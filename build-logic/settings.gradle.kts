dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    val group = "io.github.merseyside"
    val catalogVersions = "1.8.2"
    versionCatalogs {
        val catalogGradle by creating {
            from("$group:catalog-version-gradle:$catalogVersions")
        }
    }
}