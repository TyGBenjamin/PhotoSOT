object Versions {
    const val androidApp = "8.0.1"
    const val coilV = "2.3.0"
    const val constraint = "2.1.4"
    const val compose = "1.3.1"
    const val coroutine = "1.6.4"
    const val composeActivity = "1.7.2"
    const val composeBom = "2023.06.01"
    const val coreCtx = "1.10.1"
    const val coroutines = "1.3.2"
    const val dataStore = "1.0.0"
    const val dagger = "2.44.2"
    const val junit5 = "5.9.1"
    const val kotlin = "1.7.20"
    const val lifecycle = "2.6.1"
    const val material = "1.1.0-alpha02"
    const val mockk = "1.13.4"
    const val navigation = "2.6.0"
    const val room = "2.5.1"
    const val retrofit = "2.9.0"
    const val serialV = "1.5.0"
    const val jakeV = "0.8.0"
}


object Plugins {
    const val androidApp = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val navigationPlug = "androidx.navigation.safeargs.kotlin"
    const val kapt = "kotlin-kapt"
    const val daggerPlug = "com.google.dagger.hilt.android"
    const val serializerPlug = "org.jetbrains.kotlin.plugin.serialization"
}


object Libs {
    const val coil = "io.coil-kt:coil-compose:${Versions.coilV}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val coreCtx = "androidx.core:core-ktx:${Versions.coreCtx}"
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val material3 = "androidx.compose.material3:material3:${Versions.material}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val roomBase = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"
    const val retrofitBase = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConvert = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val navCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val serializerJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialV}"
    const val daggerBase = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val daggerNav = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val daggerKapt = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    const val jakeWharton =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.jakeV}"
}

    object Test {
        const val junit5 = "org.junit.jupiter:junit-jupiter"
        const val junit5Platform = "org.junit:junit-bom:${Versions.junit5}"
        const val mockk = "io.mockk:mockk-android:${Versions.mockk}"
        const val mockkAgent = "io.mockk:mockk-agent:${Versions.mockk}"
        const val coroutineTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    }

