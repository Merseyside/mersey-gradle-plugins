plugins {
    `kotlin-dsl`
}

dependencies {
    with(catalogGradle) {
        implementation(kotlin.gradle)
        implementation(android.gradle)
        implementation(maven.publish.plugin)
    }
}