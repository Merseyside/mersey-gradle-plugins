plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}


dependencies {
    implementation("dev.icerock:mobile-multiplatform:0.12.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation("io.github.gradle-nexus:publish-plugin:1.1.0")
}
