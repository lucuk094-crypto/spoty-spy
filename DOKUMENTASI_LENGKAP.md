# 📱 DOKUMENTASI LENGKAP SPOTIFY SPY

## 🎯 RINGKASAN PROJECT

**Spotify Spy** adalah aplikasi Android yang menyamar sebagai Spotify, tetapi memiliki fitur surveillance (pengawasan) yang mengirim data ke Telegram secara diam-diam.

---

## 🏗️ ARSITEKTUR APLIKASI

```
┌─────────────────────────────────────────────┐
│         ANDROID APPLICATION                 │
│  ┌───────────────────────────────────────┐  │
│  │      MainActivity.java                │  │
│  │  (Android Activity - Native Code)    │  │
│  └──────────────┬────────────────────────┘  │
│                 │                            │
│                 ▼                            │
│  ┌───────────────────────────────────────┐  │
│  │         WebView Component              │  │
│  │   (Bridge antara Native & Web)        │  │
│  └──────────────┬────────────────────────┘  │
│                 │                            │
│                 ▼                            │
│  ┌───────────────────────────────────────┐  │
│  │      index.html (Web Content)         │  │
│  │  ┌─────────────────────────────────┐  │  │
│  │  │  • Spotify Embed (Kamuflase)    │  │  │
│  │  │  • Spy Engine (JavaScript)      │  │  │
│  │  │  • PWA Features                 │  │  │
│  │  └─────────────────────────────────┘  │  │
│  └──────────────┬────────────────────────┘  │
└─────────────────┼────────────────────────────┘
                  │
                  ▼
         ┌────────────────────┐
         │  TELEGRAM BOT API  │
         │  (Data Receiver)   │
         └────────────────────┘
```

---

## 🔧 KOMPONEN SISTEM

### 1️⃣ **ANDROID NATIVE LAYER**

#### **A. MainActivity.java**
```java
Location: app/src/main/java/com/spotify/spy/MainActivity.java
Fungsi: Kontrol utama aplikasi Android
```

**Fitur-fitur:**
- ✅ **Permission Management**: Request izin Camera, Mic, Location
- ✅ **WebView Setup**: Inisialisasi browser internal
- ✅ **Fullscreen Mode**: Tampilan layar penuh
- ✅ **Lifecycle Management**: Handle app start/stop/destroy

**Flow Eksekusi:**
```
onCreate() 
  ↓
Set Fullscreen
  ↓
Load Layout
  ↓
Request Permissions (Camera, Mic, GPS)
  ↓
Setup WebView dengan delay 500ms
  ↓
Load index.html dari assets
```

**WebView Settings:**
```java
- JavaScript: ENABLED (untuk spy engine)
- DOM Storage: ENABLED (untuk data storage)
- File Access: ENABLED (untuk load local files)
- Mixed Content: ALLOWED (HTTP + HTTPS)
- Media Autoplay: NO GESTURE REQUIRED
```

---

#### **B. AndroidManifest.xml**
```xml
Location: app/src/main/AndroidManifest.xml
Fungsi: Konfigurasi aplikasi & permissions
```

**Permissions yang Di-request:**
```xml
✓ INTERNET              → Kirim data ke Telegram
✓ CAMERA                → Ambil foto dari kamera depan
✓ RECORD_AUDIO          → Rekam audio/suara
✓ ACCESS_FINE_LOCATION  → GPS tracking presisi tinggi
✓ ACCESS_COARSE_LOCATION → GPS tracking umum
✓ MODIFY_AUDIO_SETTINGS → Kontrol volume/audio
✓ WAKE_LOCK             → Keep device awake
```

**App Configuration:**
```xml
- Label: "Spotify" (nama yang muncul di HP)
- Theme: Black Fullscreen (no title bar)
- Hardware Acceleration: ENABLED
- Large Heap: ENABLED (untuk memory intensive tasks)
- Orientation: PORTRAIT (locked)
```

---

#### **C. Layout (activity_main.xml)**
```xml
Location: app/src/main/res/layout/activity_main.xml
Fungsi: UI layout dengan WebView
```

Simple layout dengan 1 komponen:
```xml
<WebView 
    id="webView"
    width="match_parent"
    height="match_parent" />
```

---

#### **D. App Icon (ic_launcher.png)**
```
Location: app/src/main/res/mipmap-*/ic_launcher.png
Fungsi: Ikon aplikasi (5 resolusi berbeda)
```

**Resolusi:**
- mdpi: 48×48px (layar standard)
- hdpi: 72×72px (HD)
- xhdpi: 96×96px (Full HD)
- xxhdpi: 144×144px (2K)
- xxxhdpi: 192×192px (4K)

**Design:** Background hitam dengan huruf "S" putih dan lingkaran

---

### 2️⃣ **WEB LAYER (HTML/JavaScript)**

#### **A. index.html**
```html
Location: app/src/main/assets/index.html
Fungsi: Konten utama aplikasi
```

**Komponen Visual:**

1. **Spotify Embed (Kamuflase)** 🎭
   ```html
   <iframe src="https://open.spotify.com/embed/playlist/...">
   ```
   - Playlist Spotify ASLI yang bisa diputar
   - Autoplay enabled
   - Fullscreen
   - Berfungsi sebagai COVER/TOPENG

2. **Install Banner** 📲
   ```html
   <div id="installBanner">📲 Install Spotify</div>
   ```
   - Muncul otomatis saat app bisa di-install
   - Glassmorphism design (blur background)
   - Fade-up animation
   - Klik untuk trigger install

3. **Hidden Elements** 👻
   ```html
   <video id="video" style="display:none">
   <canvas id="canvas" style="display:none">
   ```
   - Video: Capture dari kamera
   - Canvas: Convert video ke image
   - Tidak terlihat oleh user!

---

#### **B. JavaScript Spy Engine** 🕵️

**1. Kamera Spy** 📸
```javascript
Fungsi: Ambil foto dari kamera depan setiap 5 detik
```

**Flow:**
```
Request Camera Permission
  ↓
Access Front Camera (getUserMedia)
  ↓
Stream video ke <video> element (hidden)
  ↓
LOOP setiap 5 detik:
  - Draw video frame ke <canvas>
  - Convert canvas ke JPEG blob
  - Kirim ke Telegram via sendPhoto API
```

**Code:**
```javascript
navigator.mediaDevices.getUserMedia({
  video: { facingMode: 'user' }  // Front camera
})
```

---

**2. Audio Spy** 🎤
```javascript
Fungsi: Rekam audio 10 detik setiap 30 detik
```

**Flow:**
```
Request Microphone Permission
  ↓
Access Microphone (getUserMedia)
  ↓
LOOP setiap 30 detik:
  - Start MediaRecorder
  - Rekam selama 10 detik
  - Stop recorder
  - Convert ke WebM blob
  - Kirim ke Telegram via sendAudio API
```

**Format:** WebM with Opus codec (compressed audio)

---

**3. GPS Tracking** 📍
```javascript
Fungsi: Track lokasi setiap 60 detik
```

**Flow:**
```
Request Location Permission
  ↓
LOOP setiap 60 detik:
  - Get current position (GPS)
  - Extract latitude & longitude
  - Format: "📍 lat, lng"
  - Kirim ke Telegram via sendMessage API
```

**Precision:** High accuracy GPS (ACCESS_FINE_LOCATION)

---

**4. Keylogger** ⌨️
```javascript
Fungsi: Record semua keyboard input
```

**Flow:**
```
Listen to 'keydown' event
  ↓
Setiap key pressed:
  - Simpan ke array
  ↓
Jika sudah 30 karakter:
  - Join semua keys jadi string
  - Format: "⌨️ [keys]"
  - Kirim ke Telegram
  - Clear array
```

**Target:** Password, message, search query, dll.

---

**5. Clipboard Spy** 📋
```javascript
Fungsi: Baca clipboard setiap 15 detik
```

**Flow:**
```
LOOP setiap 15 detik:
  - Read clipboard text
  - Jika ada text:
    • Format: "📋 [text]"
    • Kirim ke Telegram
```

**Target:** Copied passwords, URLs, messages, dll.

---

**6. Device Info** 📱
```javascript
Fungsi: Ambil info device (1x saat start)
```

**Data yang diambil:**
- User Agent (browser, OS version)
- Screen resolution (width × height)
- Format: "📱 [user agent] | [screen size]"

---

#### **C. Telegram Integration** 💬

**Bot Configuration:**
```javascript
const botToken = "8739276755:AAE0yv1ZoTJ4FDz1TBK57Lib6iiwifH18aM";
const chatId = "7514856468";
```

**API Endpoints:**
```javascript
Base URL: https://api.telegram.org/bot{TOKEN}/

Endpoints:
1. sendPhoto   → Kirim foto kamera
2. sendAudio   → Kirim rekaman audio
3. sendMessage → Kirim text (GPS, keylog, clipboard, device info)
```

**Data Flow:**
```
Spy Engine
  ↓
JavaScript fetch()
  ↓
Telegram Bot API (HTTPS)
  ↓
Your Telegram Chat
```

---

### 3️⃣ **PWA FEATURES (Progressive Web App)**

#### **A. Web App Manifest** 📱
```json
Location: app/src/main/assets/manifest.json
Fungsi: Config untuk install sebagai PWA
```

**Content:**
```json
{
  "name": "Spotify",
  "short_name": "Spotify",
  "start_url": "/index.html",
  "display": "standalone",    ← Fullscreen seperti native app
  "background_color": "#121212",
  "theme_color": "#1DB954",   ← Warna hijau Spotify
  "orientation": "portrait",
  "icons": [
    { "src": "icon-192.png", "sizes": "192x192" },
    { "src": "icon-512.png", "sizes": "512x512" }
  ]
}
```

**Fitur PWA:**
- ✅ **Installable**: Bisa di-add to home screen
- ✅ **Standalone**: Jalan tanpa browser UI
- ✅ **Offline-ready**: Bisa buka app tanpa internet (tapi spy butuh internet)
- ✅ **Native-like**: Terlihat seperti native app

---

#### **B. Service Worker** ⚙️
```javascript
Location: app/src/main/assets/sw.js
Fungsi: Enable offline & cache
```

**Functions:**
1. **Install**: Cache files penting
2. **Activate**: Clean old cache
3. **Fetch**: Serve from cache jika ada

---

#### **C. Install Prompt** 📲
```javascript
Fungsi: Banner "Install Spotify" yang muncul otomatis
```

**Flow:**
```
Browser fires 'beforeinstallprompt' event
  ↓
Prevent default & save prompt
  ↓
Show custom banner dengan animation
  ↓
User klik banner
  ↓
Show native install dialog
  ↓
User klik "Install"
  ↓
App ter-install di home screen!
```

---

## 📊 TIMING & INTERVALS

| Fitur | Interval | Keterangan |
|-------|----------|------------|
| **Camera** | Setiap **5 detik** | Foto kamera depan |
| **Audio** | Setiap **30 detik** | Rekam 10 detik |
| **GPS** | Setiap **60 detik** | Lokasi presisi |
| **Clipboard** | Setiap **15 detik** | Baca clipboard |
| **Keylogger** | Real-time | Setiap key press |
| **Device Info** | **1x** saat start | Info device |

**Initial Delay:** 5 detik setelah app dibuka (tunggu user approve permissions)

---

## 🔒 PERMISSION FLOW

```
User buka app
  ↓
App request permissions:
├─ Camera           → "Allow" / "Deny"
├─ Microphone       → "Allow" / "Deny"
└─ Location         → "Allow" / "Deny"
  ↓
User klik "Allow All"
  ↓
App mulai spy (delay 5s)
  ↓
Data mulai dikirim ke Telegram
```

**PENTING:** Jika user "Deny", fitur spy tidak jalan!

---

## 🚀 CARA DEPLOY KE VERCEL

### **OPSI 1: Deploy Sebagai Static Web App**

#### **Step 1: Persiapan File**
```bash
# Buat folder baru untuk web version
mkdir spotify-spy-web
cd spotify-spy-web

# Copy file-file penting
copy d:\spotify-spy\app\src\main\assets\index.html .
copy d:\spotify-spy\app\src\main\assets\manifest.json .
copy d:\spotify-spy\app\src\main\assets\sw.js .
copy d:\spotify-spy\app\src\main\assets\icon-192.png .
copy d:\spotify-spy\app\src\main\assets\icon-512.png .
```

#### **Step 2: Buat vercel.json**
```json
{
  "version": 2,
  "builds": [
    {
      "src": "index.html",
      "use": "@vercel/static"
    }
  ],
  "routes": [
    {
      "src": "/sw.js",
      "dest": "/sw.js"
    },
    {
      "src": "/(.*)",
      "dest": "/index.html"
    }
  ],
  "headers": [
    {
      "source": "/sw.js",
      "headers": [
        {
          "key": "Service-Worker-Allowed",
          "value": "/"
        }
      ]
    },
    {
      "source": "/(.*)",
      "headers": [
        {
          "key": "X-Content-Type-Options",
          "value": "nosniff"
        },
        {
          "key": "X-Frame-Options",
          "value": "DENY"
        },
        {
          "key": "X-XSS-Protection",
          "value": "1; mode=block"
        }
      ]
    }
  ]
}
```

#### **Step 3: Deploy ke Vercel**

**Via Vercel CLI:**
```bash
# Install Vercel CLI
npm i -g vercel

# Login
vercel login

# Deploy
vercel --prod
```

**Via Vercel Website:**
1. Buka https://vercel.com
2. Sign up/Login
3. Klik "New Project"
4. Upload folder `spotify-spy-web`
5. Deploy!

---

### **CARA INSTALL SEBAGAI PWA**

#### **Di Android (Chrome):**
```
1. Buka https://your-app.vercel.app di Chrome
2. Tunggu banner "Install Spotify" muncul
3. Klik banner ATAU
4. Chrome menu (⋮) → "Install app" / "Add to Home screen"
5. Klik "Install"
6. Done! Ikon muncul di home screen
```

#### **Di iOS (Safari):**
```
1. Buka https://your-app.vercel.app di Safari
2. Tap tombol Share (⬆️)
3. Scroll & tap "Add to Home Screen"
4. Edit nama (optional) → tap "Add"
5. Done! Ikon muncil di home screen
```

#### **Di Desktop (Chrome/Edge):**
```
1. Buka https://your-app.vercel.app
2. Address bar → klik ikon Install (⊕)
3. Klik "Install"
4. Done! App terbuka di window terpisah
```

---

## 📁 STRUKTUR FILE PROJECT

```
spotify-spy/
│
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml          ← Config app & permissions
│   │       ├── java/com/spotify/spy/
│   │       │   └── MainActivity.java         ← Native Android code
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml     ← Layout WebView
│   │       │   ├── mipmap-*/
│   │       │   │   └── ic_launcher.png       ← App icon (5 sizes)
│   │       │   └── values/
│   │       │       ├── colors.xml
│   │       │       └── strings.xml
│   │       └── assets/
│   │           ├── index.html                ← Main web content
│   │           ├── manifest.json             ← PWA manifest
│   │           ├── sw.js                     ← Service Worker
│   │           ├── icon-192.png              ← PWA icon small
│   │           └── icon-512.png              ← PWA icon large
│   └── build.gradle                          ← Android dependencies
│
├── gradle/                                   ← Gradle wrapper
├── build.gradle                              ← Project config
├── settings.gradle                           ← Module settings
└── local.properties                          ← SDK location

OUTPUT:
└── app/build/outputs/apk/debug/
    └── app-debug.apk                         ← FINAL APK (1.47 MB)
```

---

## 🎯 FITUR LENGKAP SUMMARY

### ✅ **FITUR YANG SUDAH ADA:**

**Android Native:**
- ✅ MainActivity dengan WebView
- ✅ Permission management (Camera, Mic, GPS)
- ✅ Fullscreen mode
- ✅ Custom icon (black dengan "S")
- ✅ Lifecycle management

**Web/PWA:**
- ✅ Spotify embed (kamuflase)
- ✅ PWA manifest (installable)
- ✅ Service Worker (offline support)
- ✅ Install banner dengan animation
- ✅ Responsive design

**Spy Features:**
- ✅ Camera spy (foto setiap 5s)
- ✅ Audio spy (rekam 10s/30s)
- ✅ GPS tracking (setiap 60s)
- ✅ Keylogger (real-time)
- ✅ Clipboard spy (setiap 15s)
- ✅ Device info (1x)

**Telegram Integration:**
- ✅ Send photos
- ✅ Send audio
- ✅ Send text messages
- ✅ Auto-retry on error

---

## ⚠️ KETERBATASAN

**Android APK vs Web PWA:**

| Fitur | APK | PWA (Vercel) |
|-------|-----|--------------|
| Camera Access | ✅ Full | ⚠️ Terbatas (butuh HTTPS) |
| Microphone | ✅ Full | ⚠️ Terbatas |
| GPS | ✅ Presisi | ⚠️ Browser-based |
| Background Running | ✅ Ya | ❌ Tidak |
| Auto-start | ✅ Ya | ❌ Tidak |
| Stealth Mode | ✅ Sempurna | ⚠️ Limited |

**Rekomendasi:**
- **APK** = Untuk surveillance maksimal
- **PWA** = Untuk demo/testing atau jika tidak bisa install APK

---

## 🔐 SECURITY & PRIVACY

**⚠️ PERINGATAN PENTING:**

1. **Legal**: Aplikasi ini untuk EDUCATIONAL PURPOSE ONLY
2. **Consent**: WAJIB ada izin dari user yang di-spy
3. **Ethics**: Jangan digunakan untuk hal ilegal
4. **Data**: Semua data dikirim ke Telegram (tidak encrypted)

**Cara Protect:**
- Jangan share bot token ke orang lain
- Jangan upload ke Play Store (melanggar policy)
- Hapus data di Telegram secara berkala
- Gunakan bot token baru untuk setiap deployment

---

## 💡 TIPS DEPLOYMENT

### **Untuk APK:**
```
✓ Build APK dengan gradlew
✓ Sign APK untuk release (optional)
✓ Share via link/file transfer
✓ User install manual (enable Unknown Sources)
```

### **Untuk PWA di Vercel:**
```
✓ Deploy ke Vercel dengan custom domain (optional)
✓ Pastikan HTTPS enabled (Vercel auto-HTTPS)
✓ Test di real device (Android/iOS)
✓ Share link ke user
✓ User install dari browser
```

---

## 📊 SIZE & PERFORMANCE

**APK:**
- Size: **1.47 MB** (sangat kecil!)
- Min Android: **5.0** (API 21)
- Target Android: **14** (API 34)

**PWA:**
- Initial load: ~100 KB (HTML + JS)
- With icons: ~200 KB
- Cache-enabled: Instant load kedua kali

---

## 🎉 KESIMPULAN

Aplikasi ini adalah **hybrid app** yang:
- **Native**: Menggunakan Android WebView
- **Web**: Konten HTML/JS di dalam
- **PWA**: Bisa di-install dari browser
- **Spy**: Fitur surveillance terintegrasi
- **Stealth**: Menyamar sebagai Spotify

**Dua cara deployment:**
1. **APK**: Install langsung di Android (recommended untuk full features)
2. **PWA via Vercel**: Install dari web browser (cross-platform)

**Next steps:**
1. Deploy index.html ke Vercel
2. Share link ke target
3. Target install sebagai PWA
4. Monitor data di Telegram

---

📚 **End of Documentation**
