plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.daggerPlug)
    id(Plugins.serializerPlug)
}

android {
    namespace = "com.example.photosoftruth"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.photosoftruth"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(Libs.coreCtx)
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.composeActivity)
    implementation(Libs.composeUi)
    implementation(Libs.composeUiGraphics)
    implementation(Libs.composeUiToolingPreview)
    implementation(Libs.composeMaterial)
    implementation(platform(Libs.composeBom))


    //Datastore
    implementation(Libs.dataStore)

    // Room Database
    implementation(Libs.roomBase)
    implementation(Libs.roomKtx)
    kapt(Libs.roomKapt)

    //Coil For image loading
    implementation(Libs.coil)

    //DaggerHilt
    implementation(Libs.daggerBase)
    kapt(Libs.daggerKapt)

    // Retrofit
    implementation(Libs.retrofitBase)
    implementation(Libs.retrofitConvert)
    implementation(Libs.navCompose)
//    implementation 'androidx.hilt:hilt-navigation-compose:2.6.0'
    // For serialization of json to kotlin data class
    implementation(Libs.serializerJson)

    // Handles our unit test engine
    testImplementation(platform(Test.junit5Platform))
    testImplementation(Test.junit5)

    // Used for Test doubles
    testImplementation(Test.mockk)
    testImplementation(Test.mockkAgent)

    // Used for testing coroutines
    testImplementation(Test.coroutineTest)
}


