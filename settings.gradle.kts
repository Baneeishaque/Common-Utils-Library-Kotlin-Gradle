pluginManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        }
    }
}
rootProject.name = "Common-Utils-Library-Kotlin-Gradle"
include(":common-lib")
