# Storyworthy

Storyworthy is an Android application designed to help users capture and organize daily "storyworthy" moments. Inspired by the practice of Homework for Life, it provides a simple and elegant interface to record the small, meaningful events of each day.

## 🚀 Features

- **Daily Capture:** Easily add "storyworthy" moments with a dedicated interface.
- **Calendar View:** Visualize your story history and track your consistency using a calendar interface.
- **Local Storage:** All your data is stored securely on your device using Room database.
- **Modern UI:** Built entirely with Jetpack Compose for a smooth and responsive user experience.

## 🛠 Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Database:** [Room](https://developer.android.com/training/data-storage/room)
- **Navigation:** [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- **Calendar:** [Kizitonwose Calendar](https://github.com/kizitonwose/Calendar)
- **Asynchronous Programming:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)

## 🏗 Architecture

The project follows modern Android development best practices and Clean Architecture principles:

- **UI Layer:** Jetpack Compose screens and ViewModels.
- **Domain/Repository Layer:** Repository pattern to abstract data sources.
- **Data Layer:** Room database for local persistence.

## 🏁 Getting Started

### Prerequisites

- Android Studio Ladybug or newer.
- JDK 17.
- Android SDK 26+ (Android 8.0).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/storyworthy.git
   ```
2. Open the project in Android Studio.
3. Sync Project with Gradle Files.
4. Run the app on an emulator or physical device.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. (Note: Please add a LICENSE file if you intend to share this publicly).
