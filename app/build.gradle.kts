plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(apiLevel = AndroidConfig.compileSdkVersion)
    buildToolsVersion(version = AndroidConfig.buildToolsVersion)

    defaultConfig {
        applicationId(applicationId = AndroidConfig.applicationId)
        minSdkVersion(minSdkVersion = AndroidConfig.minSdkVersion)
        targetSdkVersion(targetSdkVersion = AndroidConfig.targetSdkVersion)
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(name = "proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude(pattern = "META-INF/gradle/incremental.annotation.processors")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {

    implementAndroidXCore()
    implementLifecycleHelpers()
    implementHilt()

    implementation(dependencyNotation = Dependencies.dataStore)
    implementation(dependencyNotation = Dependencies.paging)
    implementation(dependencyNotation = Dependencies.kotlin)
    implementation(dependencyNotation = Dependencies.coroutines)
    implementation(dependencyNotation = Dependencies.coroutinesCore)
    implementation(dependencyNotation = Dependencies.serializationJson)
    implementation(dependencyNotation = Dependencies.material)
    implementation(dependencyNotation = Dependencies.okHttp3)
    implementation(dependencyNotation = Dependencies.logging)
    implementation(dependencyNotation = Dependencies.retrofit)
    implementation(dependencyNotation = Dependencies.serializationConverter)
    implementation(dependencyNotation = Dependencies.coil)
    implementation(dependencyNotation = Dependencies.vkCore)
}