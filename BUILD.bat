@echo off
echo ==========================================
echo   SpotifySpy - Build Script
echo ==========================================
echo.

REM Set JAVA_HOME ke Android Studio JDK
set "JAVA_HOME=C:\Program Files\Android\Android Studio\jbr"
set "ANDROID_HOME=C:\Users\vanx3\AppData\Local\Android\Sdk"

echo Java Home: %JAVA_HOME%
echo Android SDK: %ANDROID_HOME%
echo.

REM Bersihkan build sebelumnya
echo [1/3] Membersihkan build sebelumnya...
call gradlew.bat clean
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Gradle clean gagal!
    echo.
    echo SOLUSI:
    echo 1. Buka Android Studio
    echo 2. File ^> Open ^> Pilih folder ini
    echo 3. Tunggu Gradle sync selesai
    echo 4. Build ^> Build Bundle/APK ^> Build APK
    echo.
    pause
    exit /b 1
)

echo.
echo [2/3] Building APK Debug...
call gradlew.bat assembleDebug
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Build gagal!
    echo.
    echo SOLUSI:
    echo 1. Buka Android Studio
    echo 2. File ^> Open ^> Pilih folder ini
    echo 3. Tunggu Gradle sync selesai
    echo 4. Build ^> Build Bundle/APK ^> Build APK
    echo.
    pause
    exit /b 1
)

echo.
echo [3/3] Build BERHASIL!
echo.
echo APK Location:
echo %CD%\app\build\outputs\apk\debug\app-debug.apk
echo.
echo Untuk install:
echo adb install app\build\outputs\apk\debug\app-debug.apk
echo.
pause
