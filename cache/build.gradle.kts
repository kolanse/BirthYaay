plugins {
    id  ("com.android.library")
    id ("kotlin-android")
    id(PluginDepedencies.kotlin_kapt)
    id(PluginDepedencies.dagger_hilt)
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)

        testInstrumentationRunner = AppConfig.androidTestInstrumentation

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

        freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
    }

    packagingOptions {
        
        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        excludes += "/META-INF/AL2.0"
        excludes += "/META-INF/LGPL2.1"
    }
}

dependencies {

    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //core libs
    implementation(AppDependencies.coreLibrary)

     // room
    //Room depedencies

    implementation(AppDependencies.roomImpl)
    implementation(AppDependencies.roomCoroutine)
    testImplementation(AppDependencies.roomTest)
    kapt(AppDependencies.roomKapt)


    //dagger Hilt
    implementation(AppDependencies.daggerHiltImplementation)
    kapt(AppDependencies.daggerHiltKapt)
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)

    // coroutine test
    //coroutine test
    implementation(AppDependencies.standardCoroutineAndroid)
    implementation(AppDependencies.standardCoroutineCore)
    testImplementation(AppDependencies.coroutineUnitTest)
    androidTestImplementation(AppDependencies.coroutineAndroidTest)


    //arch core testing
    testImplementation(AppDependencies.archCoreTest)
    androidTestImplementation(AppDependencies.archCoreTest)
}