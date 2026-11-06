# gifTube Android Keyboard Application - Build & Test Instructions

## Prerequisites
1. **Android Studio** (Ladybug or newer recommended)
2. **JDK 17** (or higher)
3. **Android SDK** (API 24+ for minimum, API 34 for target)
4. **Kotlin** plugin (included in Android Studio)

## Step 1: Import the Project
1. Open **Android Studio**
2. Select **"Import project (Gradle, Eclipse ADT, etc.)"**
3. Navigate to `/home/user/Giftube/app`
4. Select the `build.gradle.kts` file
5. Wait for Gradle sync to complete

## Step 2: Configure Build Environment
1. Ensure `local.properties` contains the correct SDK path:
   ```properties
   sdk.dir=/path/to/android/sdk
   ```
2. Verify `gradle.properties`:
   ```properties
   kotlin.code.style=official
   android.useAndroidX=true
   android.enableJetifier=true
   ```

## Step 3: Build the APK
1. In the **Build** menu, select **"Build Bundle(s) / APK(s)"**
2. Choose **"Build APK(s)"**
3. Android Studio will compile the project and generate:
   - `app/build/outputs/apk/debug/app-debug.apk`
4. You can find the APK in the `app/build/outputs/apk/debug/` directory

## Step 4: Install and Test on Device/Emulator
1. **Enable Developer Options** on your Android device:
   - Go to Settings > About Phone > Tap "Build Number" 7 times
2. **Enable USB Debugging**:
   - Settings > Developer Options > USB Debugging
3. **Connect Device** via USB or use Android Emulator
4. **Install APK**:
   - Run `adb install app/build/outputs/apk/debug/app-debug.apk`
   - Or drag-and-drop the APK into the emulator
5. **Enable gifTube Keyboard**:
   - Settings > System > Languages & Input > Virtual Keyboard > Manage Keyboards
   - Toggle **"gifTube Keyboard (Turkish Q)"** to ON
6. **Select gifTube Keyboard**:
   - Open any text field (e.g., Messages, Notes)
   - Tap the keyboard icon and select **gifTube Keyboard**

## Step 5: Testing Features
1. **Turkish Q Layout**:
   - Type standard characters: Q, W, E, R, T, Y, U, I, O, P
   - Test special characters: Ç, Ğ, I, İ, Ö, Ş, Ü
   - Verify Shift/Caps Lock, Backspace, Spacebar, and Enter
2. **Emoji Support**:
   - Tap the 😊 button in the tab header
   - Browse emojis and tap to insert
3. **GIF Integration**:
   - Tap the **GIF** button in the tab header
   - Search for GIFs (placeholder structure)
   - Select GIF to send via `commitContent` (requires API key integration)

## Step 6: Troubleshooting
- **Keyboard not showing**: Check if "gifTube Keyboard" is enabled in system settings
- **Permission errors**: Ensure `INTERNET` permission is granted in `AndroidManifest.xml`
- **Build failures**: Check Gradle sync logs for missing dependencies

## Technical Notes
- The project uses **Kotlin** and **AndroidX**
- GIF integration uses **Glide** library for loading
- Emoji panel uses **RecyclerView** for performance
- Turkish Q layout is implemented via `Keyboard` XML and `GifTubeKeyboardService`
