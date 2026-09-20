# PalavraSecreta

A word puzzle game for Android, developed with **Kotlin** and **Jetpack Compose**, featuring chapter-based progression, level maps, persistent player progress, and an integrated gameplay audio system.

The project focuses on clean architecture, reusable UI components, game-state management, and modern Android development practices.

## 🎮 Features

* Word puzzle gameplay
* Chapter-based progression
* Level map system
* 30 playable chapters
* Persistent player progress
* Game state management
* Integrated gameplay music
* Audio settings and control
* Lifecycle-aware audio management
* Responsive Jetpack Compose interface
* Unit tests for game logic
* Android instrumentation tests

## 🛠️ Technologies

* **Kotlin**
* **Jetpack Compose**
* **Android SDK**
* **Android Jetpack**
* **ViewModel**
* **Repository Pattern**
* **Gradle Kotlin DSL**
* **JUnit**
* **Android Instrumentation Tests**
* **MediaPlayer / Android Audio APIs**

## 🏗️ Project Structure

```text
app/src/main/java/com/cristian/palavrasecreta/
│
├── audio/
│   ├── AudioLifecycleObserver.kt
│   └── AudioManager.kt
│
├── data/
│   ├── GameLogic.kt
│   ├── content/
│   ├── model/
│   ├── progress/
│   └── settings/
│
├── ui/
│   ├── components/
│   ├── screens/
│   └── theme/
│
├── viewmodel/
│   └── GameViewModel.kt
│
└── MainActivity.kt
```

## 🧩 Game Architecture

The game separates gameplay logic from the user interface.

The `GameLogic` component is responsible for the core puzzle mechanics, while the `GameViewModel` manages game state and communicates with the Compose UI.

The project also uses dedicated repositories for player progress and audio settings, helping keep persistence and application state organized.

## 🗺️ Chapter & Level Progression

PalavraSecreta uses a chapter-based progression system.

Players advance through different stages while their progress is stored locally. The level map provides a visual representation of the player's progression through the game.

The current version contains **30 chapters**, with the architecture designed to support additional content in the future.

## 🎵 Audio System

The project includes a dedicated audio architecture for gameplay music.

The `AudioManager` handles music playback while `AudioLifecycleObserver` integrates audio behavior with the Android application lifecycle.

Audio preferences are managed separately through `AudioSettingsRepository`.

The project includes an original gameplay music asset:

```text
app/src/main/res/raw/gameplay_music.ogg
```

## 🧪 Testing

The project includes both unit and Android instrumentation tests.

### Unit Tests

Game logic is tested independently using JUnit.

```text
app/src/test/
```

### Instrumentation Tests

Android-specific functionality is covered by instrumentation tests.

```text
app/src/androidTest/
```

This approach helps verify core gameplay behavior while also testing Android application components.

## 📱 Android Configuration

* **Minimum SDK:** 24
* **Target SDK:** 37
* **Compile SDK:** 37
* **Language:** Kotlin
* **UI:** Jetpack Compose

## 🚀 Getting Started

Clone the repository:

```bash
git clone https://github.com/cristianevertondev/PalavraSecreta.git
```

Open the project in **Android Studio**, allow Gradle to synchronize, and run the application on an Android emulator or compatible physical device.

## 🎯 Project Goals

PalavraSecreta was developed as part of the **ELDREON STUDIOS** Android portfolio, with a focus on:

* Modern Android development
* Kotlin and Jetpack Compose
* Game development fundamentals
* State management
* Local persistence
* Audio lifecycle management
* Testable game logic
* Maintainable project architecture

## 👨‍💻 Developer

**Cristian Everton**
Android Developer & Founder of **ELDREON STUDIOS**

### Links

* GitHub: https://github.com/cristianevertondev
   LinkedIn:www.linkedin.com/in/cristian-everton-30388b438
---

*PalavraSecreta is a portfolio project developed with Kotlin and Jetpack Compose.*
