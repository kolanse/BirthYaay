import AppConfig.buildToolsVersion

plugins {
    id(PluginDepedencies.androidApplication)
    kotlin(PluginDepedencies.kotAndroid)

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

    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}

