// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(dependencyNotation = "com.android.tools.build:gradle:4.1.3")
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-serialization:1.3.72")
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