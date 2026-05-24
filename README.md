# Alien's Diary 👽📔

Alien's Diary is a cloud-synced multimedia journal application for Android. It is designed to store and organize personal photos, videos, and audio recordings using Dropbox storage.

> ⚠️ **Project Status: Incomplete (Prototype)**
> This project was originally developed in 2021 and is being hosted in 2026. It is not yet complete and is a prototype.

## 🚀 Features (Current State)

- **Navigation**: Established tabbed interface for Photos, Videos, and Audios.
- **Dropbox Foundation**: Core SDK integration for fetching file metadata.
- **Verification Layer**: Prototype entry screen for access control.
- **Photo Preview**: Experimental single-photo fetch and display logic.

## 🚧 Known Incompleteness

- **Audio & Video**: Fragment logic for these sections is currently empty.
- **Media Gallery**: No RecyclerView implementation; photo browsing is limited to single-file testing.
- **Authentication**: `VerificationActivity` uses hardcoded logic and lacks real session security.
- **Dropbox Config**: `ACCESS_TOKEN` is currently empty in `DropboxRepository`.
- **UI/UX**: FAB actions and progress indicators are not yet fully implemented.

## 🛠️ Tech Stack

- **Language**: Java
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Components**: 
  - `ViewPager2` & `TabLayoutMediator`
  - `Material Components`
  - `ConstraintLayout`
- **Networking/Storage**: 
  - `Dropbox Core SDK` (v5.1.1)
  - `Retrofit`
  - `Glide` (for image loading)
- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 32 (Android 12)

## 📦 Project Structure

```text
com.naimur.aliensdiary
├── core                # Base classes, Repositories, and Listeners
│   ├── data
│   │   └── repository  # DropboxRepository handling cloud operations
│   └── listener        # Generic callback listeners
├── feature             # Feature-specific modules
│   ├── main            # MainActivity and Tab adapters
│   ├── photo           # Photo viewing and management
│   ├── audio           # Audio diary management
│   ├── video           # Video diary management
│   └── verification    # Security/Login screen
└── util                # Helper classes (DropboxUtils, Utils)
```

## ⚙️ Setup & Configuration

### Prerequisites
1. Android Studio (Chipmunk or newer recommended).
2. A Dropbox Developer account.

### 🔑 API Keys
The application requires a Dropbox API key.
1. Create a `local.properties` file in the root directory (if it doesn't exist).
2. Add your Dropbox key:
   ```properties
   DROPBOX_KEY=your_dropbox_api_key_here
   ```
   The `build.gradle` is configured to read this key and inject it into the `BuildConfig`.

### 📂 Directory Structure in Dropbox
The app expects the following folder structure in your Dropbox root:
```text
/AlienDiary/
├── photos/
├── videos/
└── audios/
```

## 🛠️ Building the Project

Run the following command to build the project using Gradle:
```bash
./gradlew assembleDebug
```
