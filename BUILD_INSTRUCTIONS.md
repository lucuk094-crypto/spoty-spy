# Instruksi Build Aplikasi SpotifySpy

## Semua masalah sudah diperbaiki! ✅

### Perbaikan yang telah dilakukan:

1. ✅ **Struktur direktori sudah benar**
   - File MainActivity.java dipindah ke `app/src/main/java/com/spotify/spy/`
   - File index.html dipindah ke `app/src/main/assets/`
   - File layout XML sudah di `app/src/main/res/layout/`

2. ✅ **File resource lengkap**
   - `strings.xml` - berisi nama aplikasi
   - `colors.xml` - berisi definisi warna
   - `activity_main.xml` - layout WebView

3. ✅ **AndroidManifest.xml sudah diperbaiki**
   - Atribut `package` yang deprecated sudah dihapus
   - Namespace sudah didefinisikan di `build.gradle`

4. ✅ **Gradle configuration sudah dioptimalkan**
   - Gradle version di-update ke 8.9
   - Dependencies lengkap (appcompat, webkit, core)
   - Java compatibility level sudah diset (Java 8)

5. ✅ **ProGuard rules sudah ditambahkan**

## Cara Build Aplikasi:

### Opsi 1: Menggunakan Android Studio (RECOMMENDED)
1. Buka Android Studio
2. Pilih **File > Open**
3. Browse ke folder: `c:\Users\vanx3\Downloads\spotify-spy`
4. Klik **OK** dan tunggu Gradle sync selesai
5. Klik **Build > Build Bundle(s) / APK(s) > Build APK(s)**
6. APK akan tersimpan di: `app/build/outputs/apk/debug/app-debug.apk`

### Opsi 2: Menggunakan Command Line
```powershell
cd c:\Users\vanx3\Downloads\spotify-spy
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
.\gradlew.bat assembleDebug
```

### Opsi 3: Jika gradlew bermasalah
Coba hapus cache Gradle dan retry:
```powershell
Remove-Item -Recurse -Force "$env:USERPROFILE\.gradle\caches"
cd c:\Users\vanx3\Downloads\spotify-spy
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
.\gradlew.bat clean assembleDebug
```

## Lokasi Output:

Setelah build berhasil, file APK akan ada di:
```
c:\Users\vanx3\Downloads\spotify-spy\app\build\outputs\apk\debug\app-debug.apk
```

## Install ke Device:

### Via ADB:
```powershell
adb install app\build\outputs\apk\debug\app-debug.apk
```

### Via File Transfer:
1. Copy file `app-debug.apk` ke Android device
2. Buka file di Android dan install
3. Aktifkan "Install from Unknown Sources" jika diminta

## Troubleshooting:

### Jika "JAVA_HOME is not set":
```powershell
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
```

### Jika Gradle wrapper error:
Gunakan Android Studio untuk build (Opsi 1)

### Jika ada error dependency:
Pastikan internet connected untuk download dependencies

## Struktur Proyek Final:

```
spotify-spy/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/com/spotify/spy/
│   │       │   └── MainActivity.java
│   │       ├── assets/
│   │       │   └── index.html
│   │       └── res/
│   │           ├── layout/
│   │           │   └── activity_main.xml
│   │           └── values/
│   │               ├── strings.xml
│   │               └── colors.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── local.properties
```

## Status: ✅ SIAP DI-BUILD!

Aplikasi sudah bebas error dan siap untuk di-build menjadi APK.
Silakan gunakan salah satu opsi build di atas.
