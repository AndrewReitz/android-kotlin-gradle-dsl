rootProject.name = "test-project"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        maven { url = uri("https://maven.fabric.io/public") }
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id in arrayOf("kotlin-android", "kotlin-kapt", "kotlin-android-extensions")) {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }

            if (requested.id.id == "com.android.application") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}
