import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val coreKtx = "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
    const val activity = "androidx.activity:activity-ktx:${Version.ACTIVITY}"
    const val fragment = "androidx.fragment:fragment-ktx:${Version.FRAGMENT}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT}"

    const val dataStore = "androidx.datastore:datastore-preferences:${Version.DATA_STORE}"

    const val paging = "androidx.paging:paging-runtime:${Version.PAGING}"

    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val serializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.SERIALIZATION_JSON}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_CORE}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.HILT}"

    const val material = "com.google.android.material:material:${Version.MATERIAL}"

    const val okHttp3 = "com.squareup.okhttp3:okhttp:${Version.OKHTTP3}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP3}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"

    const val serializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.SERIALIZATION_CONVERTER}"

    const val coil = "io.coil-kt:coil:${Version.COIL}"

    const val vkCore = "com.vk:android-sdk-core:${Version.VK_CORE}"
}

fun DependencyHandler.implementAndroidXCore() {
    implementation(dependencyNotation = Dependencies.coreKtx)
    implementation(dependencyNotation = Dependencies.appCompat)
    implementation(dependencyNotation = Dependencies.activity)
    implementation(dependencyNotation = Dependencies.fragment)
    implementation(dependencyNotation = Dependencies.constraint)
}

fun DependencyHandler.implementLifecycleHelpers() {
    implementation(dependencyNotation = Dependencies.runtime)
    implementation(dependencyNotation = Dependencies.viewModel)
}

fun DependencyHandler.implementHilt() {
    implementation(dependencyNotation = Dependencies.hiltAndroid)
    kapt(dependencyNotation = Dependencies.hiltCompiler)
}
