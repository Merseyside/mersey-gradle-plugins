plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    with(catalogGradle) {
        implementation(moko.mobileMultiplatform)
        implementation(kotlin.gradle)
        implementation(android.gradle)
        implementation(nexusPublish)
    }
}
