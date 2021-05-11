
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {

        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {

        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktLint}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}