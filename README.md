# Modify - Modern Music Streaming App

![Android](https://img.shields.io/badge/Android-7.0+-green.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-purple.svg)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-latest-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

**Modify** is a modern, feature-rich Android music streaming application built with Kotlin and Jetpack Compose. It provides a premium user experience with Material Design 3, smooth animations, and powerful music playback capabilities.

## Features

### 🎵 Music Playback
- Full-featured music player with play, pause, skip controls
- Background playback support
- Lock screen controls
- MediaSession integration
- Queue management
- Shuffle and repeat modes
- Seek functionality

### 🏠 Home Screen
- Personalized recommendations
- Recently played songs
- Trending music
- New releases
- Featured playlists

### 🔍 Search
- Search songs, albums, and artists
- Search history
- Instant suggestions
- Voice search support (optional)

### 📚 Library
- Your playlists
- Liked songs
- Albums collection
- Artists library
- Downloaded music
- Play history

### ⬇️ Downloads
- Offline playback support
- Multiple quality options
- Download management
- Storage optimization

### ⚙️ Settings
- Dark/Light theme toggle
- Dynamic colors (Android 12+)
- Audio quality settings
- Cache management
- Storage management

## Architecture

Modify follows clean architecture principles:

```
┌─────────────────────────────────────┐
│           UI Layer                  │
│  (Composables, ViewModels, State)   │
├─────────────────────────────────────┤
│         Domain Layer                │
│      (Use Cases, Models)            │
├─────────────────────────────────────┤
│          Data Layer                 │
│  (Repositories, DAOs, API, Cache)   │
└─────────────────────────────────────┘
```

### Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose, Material Design 3
- **Architecture**: MVVM + Repository Pattern
- **DI**: Hilt
- **Async**: Coroutines + Flow/StateFlow
- **Local DB**: Room
- **Preferences**: DataStore
- **Media Player**: Media3 (ExoPlayer)
- **Image Loading**: Coil
- **Navigation**: Navigation Compose

## Project Structure

```
modify-app/
├── app/
│   └── src/main/kotlin/com/modify/music/
│       ├── data/
│       │   ├── dao/          # Data Access Objects
│       │   ├── database/     # Room Database
│       │   ├── model/        # Data models
│       │   ├── remote/       # API clients
│       │   └── repository/   # Repositories
│       ├── di/               # Dependency Injection
│       ├── service/          # Background services
│       ├── ui/
│       │   ├── components/   # Reusable UI components
│       │   ├── navigation/   # Navigation setup
│       │   ├── screens/      # App screens
│       │   └── theme/        # Theme configuration
│       ├── viewmodel/        # ViewModels
│       └── ModifyApplication.kt
├── core/                     # Core module (backend foundation)
├── .github/workflows/        # CI/CD workflows
└── build.gradle.kts          # Build configuration
```

## Requirements

- Android Studio Hedgehog or later
- JDK 17
- Android SDK 35
- Minimum Android version: 7.0 (API 24)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/sheikhdipuraihan-sudo/modify.git
cd modify/modify-app
```

2. Open in Android Studio

3. Sync Gradle files

4. Run on device or emulator:
```bash
./gradlew installDebug
```

## Building

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Android App Bundle
```bash
./gradlew bundleRelease
```

## Configuration

### Audio Quality Options
- Low: ~64 kbps
- Medium: ~128 kbps
- High: ~256 kbps
- Very High: ~320 kbps

### Supported Formats
- MP3
- AAC
- FLAC (if supported by core)
- OGG

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Code Style

This project follows the [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built upon the core engine from [modify](https://github.com/sheikhdipuraihan-sudo/modify)
- Inspired by modern music streaming applications
- Thanks to all open-source contributors

## Screenshots

_Screenshots will be added soon_

## Roadmap

- [ ] Android Auto support
- [ ] Wear OS companion app
- [ ] Lyrics display
- [ ] Equalizer integration
- [ ] Crossfade playback
- [ ] Gapless playback
- [ ] Chromecast support
- [ ] Social sharing features

---

Made with ❤️ using Kotlin and Jetpack Compose
