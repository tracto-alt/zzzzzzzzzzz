package com.smsgateway.pro;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;
import com.klinker.android.send_message.Message;

import fi.iki.elonen.NanoHTTPD;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

public class MainActivity extends Activity {
    private SMSServer server;
    private TextView statusText;
    private TextView ipText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        statusText = findViewById(R.id.statusText);
        ipText = findViewById(R.id.ipText);
        
        // Demander permissions
        requestPermissions();
        
        // Démarrer le serveur HTTP
        try {
            server = new SMSServer(8080, this);
            server.start();
            
            String ip = getIPAddress();
            statusText.setText("✅ Serveur actif");
            ipText.setText("📡 http://" + ip + ":8080");
            
            Toast.makeText(this, "🚀 Gateway démarré !", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            statusText.setText("❌ Erreur serveur");
            Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    private void requestPermissions() {
        String[] permissions = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.INTERNET
        };
        ActivityCompat.requestPermissions(this, permissions, 1);
    }
    
    private String getIPAddress() {
        try {
            for (NetworkInterface intf : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress addr : Collections.list(intf.getInetAddresses())) {
                    if (!addr.isLoopbackAddress() && addr.getAddress().length == 4) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            return "0.0.0.0";
        }
        return "0.0.0.0";
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (server != null) {
            server.stop();
        }
    }
    
    // Classe serveur HTTP
    private static class SMSServer extends NanoHTTPD {
        private MainActivity activity;
        
        public SMSServer(int port, MainActivity activity) {
            super(port);
            this.activity = activity;
        }
        
        @Override
        public Response serve(IHTTPSession session) {
            String uri = session.getUri();
            Method method = session.getMethod();
            
            // CORS headers
            Map<String, String> headers = new HashMap<>();
            headers.put("Access-Control-Allow-Origin", "*");
            headers.put("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            headers.put("Access-Control-Allow-Headers", "Content-Type");
            
            if (method == Method.OPTIONS) {
                return newFixedLengthResponse(Response.Status.OK, "text/plain", "");
            }
            
            // Health check
            if (uri.equals("/health") && method == Method.GET) {
                JSONObject response = new JSONObject();
                try {
                    response.put("status", "ok");
                    response.put("service", "SMS Gateway Pro");
                    response.put("version", "2.0");
                    Response r = newFixedLengthResponse(Response.Status.OK, "application/json", response.toString());
                    for (Map.Entry<String, String> h : headers.entrySet()) {
                        r.addHeader(h.getKey(), h.getValue());
                    }
                    return r;
                } catch (Exception e) {
                    return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", e.getMessage());
                }
            }
            
            // Endpoint /message
            if (uri.equals("/message") && method == Method.POST) {
                try {
                    // Lire le body JSON
                    Map<String, String> files = new HashMap<>();
                    session.parseBody(files);
                    String body = files.get("postData");
                    
                    JSONObject json = new JSONObject(body);
                    String message = json.getString("message");
                    JSONArray phoneNumbers = json.getJSONArray("phoneNumbers");
                    String type = json.optString("type", "sms").toLowerCase();
                    
                    JSONArray results = new JSONArray();
                    
                    // Envoyer à chaque numéro
                    for (int i = 0; i < phoneNumbers.length(); i++) {
                        String phone = phoneNumbers.getString(i);
                        
                        JSONObject result = new JSONObject();
                        result.put("phone", phone);
                        
                        try {
                            if (type.equals("mms")) {
                                activity.sendMMS(phone, message);
                                result.put("success", true);
                                result.put("type", "MMS");
                            } else {
                                activity.sendSMS(phone, message);
                                result.put("success", true);
                                result.put("type", "SMS");
                            }
                        } catch (Exception e) {
                            result.put("success", false);
                            result.put("error", e.getMessage());
                        }
                        
                        results.put(result);
                    }
                    
                    JSONObject response = new JSONObject();
                    response.put("results", results);
                    
                    Response r = newFixedLengthResponse(Response.Status.ACCEPTED, "application/json", response.toString());
                    for (Map.Entry<String, String> h : headers.entrySet()) {
                        r.addHeader(h.getKey(), h.getValue());
                    }
                    return r;
                    
                } catch (Exception e) {
                    JSONObject error = new JSONObject();
                    try {
                        error.put("error", e.getMessage());
                    } catch (Exception ignored) {}
                    return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "application/json", error.toString());
                }
            }
            
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not found");
        }
    }
    
    // Envoyer SMS
    public void sendSMS(String phone, String message) throws Exception {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, null, null);
    }
    
    // Envoyer MMS avec android-smsmms lib
    public void sendMMS(String phone, String message) throws Exception {
        Settings settings = new Settings();
        settings.setUseSystemSending(true);
        
        Transaction transaction = new Transaction(this, settings);
        
        Message mmsMessage = new Message(message, phone);
        mmsMessage.setSubject("MMS"); // Force MMS avec sujet
        
        transaction.sendNewMessage(mmsMessage, Transaction.NO_THREAD_ID);
    }
}
