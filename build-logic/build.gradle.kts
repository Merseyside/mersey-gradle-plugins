plugins {
    `kotlin-dsl`
}

dependencies {
    with(catalogGradle) {
        implementation(moko.mobileMultiplatform)
        implementation(kotlin.gradle)
        implementation(android.gradle)
        implementation(nexusPublish)
    }
}
