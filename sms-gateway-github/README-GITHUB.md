# 🚀 SMS Gateway Pro - Compilation Automatique GitHub

## ✅ Ce que ça fait :

**GitHub Actions va compiler l'APK automatiquement** dès que tu push le code !

- ✅ Gratuit
- ✅ Automatique
- ✅ Pas besoin d'Android Studio
- ✅ APK téléchargeable en 5-10 minutes
- ✅ **Fix automatique du gradle-wrapper intégré**

---

## 🔧 Note importante :

Le `gradle-wrapper.jar` est manquant dans ce projet (c'est normal pour un repo Git).

**→ Le workflow GitHub Actions le génère automatiquement !**

Tu n'as rien à faire, ça marchera direct quand tu push sur GitHub.

📝 Si tu veux compiler localement, voir le fichier **FIX-WRAPPER.md**

---

## 📋 Étapes ULTRA SIMPLES :

### 1️⃣ Crée un compte GitHub (si tu en as pas)

Va sur : https://github.com/signup

- Crée ton compte (gratuit)
- Vérifie ton email

### 2️⃣ Crée un nouveau repo (dépôt)

Une fois connecté :

1. Clique sur **"+"** en haut à droite
2. **"New repository"**
3. **Nom** : `sms-gateway-pro` (ou ce que tu veux)
4. **Public** ou **Private** (comme tu veux)
5. ❌ **NE coche PAS** "Add a README"
6. **Create repository**

### 3️⃣ Upload les fichiers

Tu as 2 options :

#### **Option A : Via le site web (plus simple)** 💚

1. Sur la page de ton repo vide
2. Clique sur **"uploading an existing file"**
3. **Drag & drop** TOUT le contenu du dossier `sms-gateway-github`
4. Descends en bas
5. **Commit changes**

#### **Option B : Via Git (si tu connais)**

```bash
cd sms-gateway-github
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/TON-USERNAME/sms-gateway-pro.git
git push -u origin main
```

### 4️⃣ Attends la compilation

Dès que tu push :

1. Va dans l'onglet **"Actions"** de ton repo
2. Tu verras un workflow **"Build Android APK"** qui tourne
3. ⏱️ **Attends 5-10 minutes**
4. ✅ Quand c'est vert, c'est prêt !

### 5️⃣ Télécharge l'APK

1. Clique sur le workflow terminé (vert ✅)
2. Descends en bas
3. Section **"Artifacts"**
4. **Clique sur "app-debug"**
5. 📥 **Télécharge le ZIP**
6. **Extrais** → Tu as ton **APK** !

---

## 📲 Installation sur le S22

1. **Copie l'APK** sur le S22 (USB, Telegram, email...)
2. **Installe** l'APK
3. **Autorise** toutes les permissions
4. **Lance l'app**
5. **Note l'IP** affichée

---

## 🚀 Utilisation

### Sur le PC :
- Webapp → ⚙️
- URL : `http://[IP_DU_S22]:8080`
- Test → ✅

### Envoi :
- **Mode SMS** → SMS pur
- **Mode MMS** → MMS automatique
- **Mode Auto** → Smart switch

---

## 🔧 Si ça compile pas

### Workflow rouge ❌

1. Va dans **Actions**
2. Clique sur le workflow rouge
3. Regarde l'erreur
4. **Dis-moi l'erreur** et je fixe

### Pas d'onglet Actions

- Ton repo est **Private**
- Va dans **Settings** → **Actions** → **Enable**

---

## 💡 Avantages GitHub Actions

✅ **Gratuit** (2000 minutes/mois)
✅ **Automatique** (compile à chaque push)
✅ **Propre** (pas besoin d'Android Studio sur ton PC)
✅ **Historique** (garde tous les APK compilés)

---

## 🎯 Résumé rapide :

1. Crée compte GitHub
2. Crée repo
3. Upload les fichiers
4. Attends 5-10 min
5. Télécharge l'APK
6. Installe sur S22

**C'EST TOUT !** 🔥

---

## 📁 Structure du projet

```
sms-gateway-github/
├── .github/
│   └── workflows/
│       └── build.yml          ← Config GitHub Actions
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/...       ← Code Java
│   │       ├── res/...        ← Layouts
│   │       └── AndroidManifest.xml
│   └── build.gradle           ← Config app
├── gradle/
│   └── wrapper/...            ← Gradle wrapper
├── build.gradle               ← Config racine
├── settings.gradle            ← Settings
├── gradlew                    ← Script Gradle
└── gradle.properties          ← Properties

```

---

## 🤔 Besoin d'aide ?

**Dis-moi où t'es bloqué et je t'aide !** 💪

GitHub Actions c'est la **meilleure solution gratuite** pour compiler sans galère ! 🚀
