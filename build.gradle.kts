// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}