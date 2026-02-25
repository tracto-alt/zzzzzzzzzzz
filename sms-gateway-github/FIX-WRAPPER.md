# 🔧 FIX : Erreur gradle-wrapper.jar manquant

## ❌ Problème :
```
Error: Could not find or load main class org.gradle.wrapper.GradleWrapperMain
Caused by: java.lang.ClassNotFoundException: org.gradle.wrapper.GradleWrapperMain
```

## ✅ Solution automatique (GitHub Actions) :

Le workflow a été **modifié automatiquement** pour installer le wrapper manquant.

**Aucune action requise de ta part !**

Quand tu push sur GitHub, le workflow va :
1. Installer Gradle temporairement
2. Générer le wrapper manquant
3. Compiler l'APK normalement

---

## 🔧 Solution manuelle (si tu compiles localement) :

### Option 1 : Utiliser Gradle global

```bash
# Installe Gradle sur ton système
sudo apt-get install gradle  # Linux
brew install gradle          # macOS

# Génère le wrapper
cd sms-gateway-github
gradle wrapper --gradle-version 8.0

# Maintenant tu peux compiler
./gradlew assembleDebug
```

### Option 2 : Télécharger le wrapper manuellement

```bash
cd gradle/wrapper
wget https://raw.githubusercontent.com/gradle/gradle/master/gradle/wrapper/gradle-wrapper.jar
cd ../..
chmod +x gradlew
./gradlew assembleDebug
```

### Option 3 : Utiliser Android Studio

**C'est la plus simple !**

1. Ouvre le projet dans Android Studio
2. Android Studio va **automatiquement** télécharger et configurer Gradle
3. Build → Build APK

---

## 🎯 Recommandation :

**→ Utilise GitHub Actions** (déjà configuré pour toi)

C'est gratuit, automatique, et ça marche à tous les coups !

1. Upload le projet sur GitHub
2. Le workflow génère le wrapper automatiquement
3. Télécharge l'APK compilé

**Aucune installation nécessaire sur ton PC !** 🚀
