import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.file.ConfigurableFileTree


object AppDependencies {
    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //android ui
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    private val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    private val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    private val lottieAnimation = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    private val amulLibrary =
        "com.amulyakhare:com.amulyakhare.textdrawable:${Versions.amulLibraryVersion}"
    val glide = "com.github.bumptech.glide:${Versions.glideVersion}"
    val glideCore = "android.arch.lifecycle:compiler:1.0.0"
    val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"


    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private val navigationTest = "androidx.navigation:navigation-testing:${Versions.nav_version}"
    private val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    // coroutines test
    val standardCoroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_test}"
    val standardCoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_test}"
    val coroutineUnitTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"
    val coroutineAndroidTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"

    //arch core test
    val archCoreTest = "androidx.arch.core:core-testing:${Versions.arch_core}"


    //Project Libraries
    val presentation = ":presentation"
    val cache = ":cache"
    val data = ":data"
    val domain = ":domain"
    val remote = ":remote"
    val navigation = ":navigation"


    //Dagger hilt
    val daggerHiltImplementation = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    val daggerHiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"

    // Room depedencies
    val roomImpl = "androidx.room:room-runtime:${Versions.room_version}"
    val roomKapt = "androidx.room:room-compiler:${Versions.room_version}"
    val roomCoroutine = "androidx.room:room-ktx:${Versions.room_version}"
    val roomTest = "androidx.room:room-testing:${Versions.room_version}"


    val coreLibrary = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(activityKtx)
    }

    val appLibraries = arrayListOf<String>().apply {
        add(appcompat)
        add(constraintLayout)
        add(materialDesign)
        add(navigationFragment)
        add(navigationUi)
        add(lottieAnimation)
        add(amulLibrary)
        add(fragmentKtx)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(navigationTest)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}


fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.annotationProcessor(list: List<String>) {
    list.forEach { dependency ->
        add("annotationProcessor", dependency)
    }
}


fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

object PluginDepedencies {
    //plugins
    val androidApplication = "com.android.application"
    val kotAndroid = "android"
    val kotlin_kapt = "kotlin-kapt"
    val dagger_hilt = "dagger.hilt.android.plugin"
}