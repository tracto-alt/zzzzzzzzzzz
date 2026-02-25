# 📱 GUIDE SUPER SIMPLE - Compilation APK

## 🎯 Étape par étape (sans se perdre)

### 1️⃣ Télécharge le ZIP et extrait-le

Tu as le fichier : `sms-gateway-pro-v2-COMPLET.zip`

**Extrais-le** sur ton Bureau → Tu auras un dossier `android-app-v2-fixed`

---

### 2️⃣ Ouvre Android Studio

Lance Android Studio

**SI tu as jamais créé de projet :**
- Clique sur **"Open"**
- Sélectionne le dossier **`android-app-v2-fixed`** (celui que tu as extrait)
- Clique **"OK"**

---

### 3️⃣ Attends que Gradle sync

Android Studio va automatiquement :
- Télécharger les dépendances
- Configurer le projet
- ⏱️ **Ça prend 5-10 minutes la première fois**

Tu verras en bas : **"Gradle sync in progress..."**

**Attends que ça finisse** (la barre de progression disparaît)

---

### 4️⃣ Compile l'APK

Quand Gradle a fini :

1. **Menu** → **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
2. **Attends 2-3 minutes**
3. ✅ Tu verras : **"APK(s) generated successfully"**
4. **Clique sur "locate"** → Ça ouvre le dossier avec l'APK

---

### 5️⃣ L'APK est prêt !

Tu as maintenant : **`app-debug.apk`**

**Copie-le sur ton S22** (via USB, Telegram, email...)

---

## 📲 Installation sur le S22

1. **Ouvre l'APK** sur le S22
2. **Autorise "Sources inconnues"** si demandé
3. **Installe**
4. **Lance l'app**
5. **Autorise TOUTES les permissions** (SMS, Téléphone, etc.)

---

## 🚀 Utilisation

### Sur le S22 :
- Lance l'app
- Note l'IP affichée (ex: `http://10.43.46.85:8080`)

### Sur le PC :
- Ta webapp → ⚙️ Paramètres
- URL : `http://10.43.46.85:8080`
- Pas de username/password
- **Test** → Ça doit dire "Connexion OK" ✅

---

## ❌ Si ça marche pas dans Android Studio

### Erreur "Plugin not found"
→ **Tu as ouvert le mauvais dossier !**
- Ferme Android Studio
- **Ouvre le dossier `android-app-v2-fixed`** (pas le sous-dossier app !)

### Erreur "SDK not found"
→ Android Studio doit télécharger le SDK
- File → Settings → Appearance & Behavior → System Settings → Android SDK
- **Install** le SDK Platform 33

### Gradle sync failed
→ Clique sur **"Try Again"** plusieurs fois
→ Ou **File** → **Invalidate Caches** → **Restart**

---

## 💡 Trop galère ?

**DIS-MOI et je te compile l'APK direct !** 

Tu l'installes juste sur le S22 et c'est prêt ! 💪

Plus simple, plus rapide ! 🚀
