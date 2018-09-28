import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application") version "3.3.0-alpha12"
    id("kotlin-android") version "1.2.70"
    id("kotlin-kapt") version "1.2.70"
    id("kotlin-android-extensions") version "1.2.70"
}

repositories {
    google()
    jcenter()
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.2")

    signingConfigs {
        getByName("debug") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    defaultConfig {
        applicationId = "cash.andrew.example"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "🔥"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    sourceSets {
        getByName("main") { java.srcDirs(file("./src/main/kotlin")) }
        getByName("test") { java.srcDirs(file("src/test/kotlin")) }
    }
}

val retrofitVersion by extra { "2.4.0" }

dependencies {
    implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))

    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha2")
    implementation("androidx.annotation:annotation:1.0.0")
    implementation("androidx.appcompat:appcompat:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0")

    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0-alpha06")
    implementation("android.arch.navigation:navigation-ui-ktx:1.0.0-alpha06")
    implementation("com.google.android.material:material:1.0.0")

    implementation("com.google.dagger:dagger:2.17")
    kapt("com.google.dagger:dagger-compiler:2.17")

    implementation("com.squareup.okhttp3:okhttp:3.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.10.0")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation("com.squareup.moshi:moshi-kotlin:1.7.0")

    implementation("com.jakewharton.rxbinding2:rxbinding:2.1.1")
    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("io.reactivex.rxjava2:rxjava:2.2.2")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0")

    implementation("com.uber.autodispose:autodispose-ktx:1.0.0-RC2")
    implementation("com.uber.autodispose:autodispose-android:1.0.0-RC2")
    implementation("com.uber.autodispose:autodispose-android-ktx:1.0.0-RC2")
    implementation("com.uber.autodispose:autodispose-android-archcomponents-ktx:1.0.0-RC2")

    implementation("com.jakewharton.threetenabp:threetenabp:1.1.0")

    implementation("com.f2prateek.rx.preferences2:rx-preferences:2.0.0")

    implementation("io.github.kobakei:ratethisapp:1.2.0")

    implementation("com.crashlytics.sdk.android:crashlytics:2.9.5")

    testImplementation("org.amshove.kluent:kluent-android:1.38")
}

kapt.useBuildCache = true

val installAll: Task = tasks.create("installAll")
installAll.description = "Install all applications."
android.applicationVariants.all {
    installAll.dependsOn(install)
    // Ensure we end up in the same group as the other install tasks.
    installAll.group = "install"
}

tasks["lint"].enabled = properties["runLint"] == "true"

