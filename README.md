# datapad-5e
Multiplatform mobile application for quick referencing and managing Force/Tech powers for your [SW5e](https://sw5e.com) games.
This is a Kotlin Multiplatform project targeting Android, iOS. iOS is not yet functioning due to Kotlin 2.0 updates.

## technology
- [Kotlin and Compose Multiplatform](https://www.jetbrains.com/kotlin-multiplatform/)
- [Ktor](https://ktor.io/)
- [Koin](https://insert-koin.io/)

## data
The project uses official SW5e data with some help from generative AI for power images.

# project roadmap
- **Completed**
  - Users can view all the powers available currently
  - Users can view specifics about each power upon tap
- **In progress**
  - Appropriate images for all powers
  - Dependency Injection considerations
- **Future features**
  - Locally stored data to prevent extra data usage
  - Searchable powers
  - Screen showing current favorites by action for actual sessions of play 

# project structure
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.
