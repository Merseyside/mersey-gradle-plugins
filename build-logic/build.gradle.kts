plugins {
    `kotlin-dsl`
}

dependencies {
    with(catalogGradle) {
        implementation(kotlin.gradle)
        implementation(android.gradle.stable)
        implementation(maven.publish.plugin)
    }
}