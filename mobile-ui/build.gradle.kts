import AppConfig.buildToolsVersion

plugins {
    id(PluginDepedencies.androidApplication)
    kotlin(PluginDepedencies.kotAndroid)
    id(PluginDepedencies.kotlin_kapt)
    id(PluginDepedencies.dagger_hilt)
    id("kotlin-android")
    id ("androidx.navigation.safeargs.kotlin")
    id ( "kotlin-parcelize")
}

 android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.example.birthyaay"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

     buildFeatures {
         viewBinding = true
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
}

dependencies {

    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //core libs

    implementation(AppDependencies.coreLibrary)
    //app libs
    implementation(AppDependencies.appLibraries)


    //project libraries
    implementation(project(AppDependencies.presentation))
    implementation(project(AppDependencies.cache))
    implementation(project(AppDependencies.data))
    implementation(project(AppDependencies.domain))
    implementation(project(AppDependencies.remote))
    implementation(project(AppDependencies.navigation))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("io.coil-kt:coil:${Versions.coilVersion}")
    implementation("com.android.support:palette-v7:${Versions.paletteVersion}")

    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)

    //dagger Hilt
    implementation(AppDependencies.daggerHiltImplementation)
    kapt(AppDependencies.daggerHiltKapt)
}

