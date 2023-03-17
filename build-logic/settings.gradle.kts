pluginManagement {
    repositories {
        mavenCentral()
        google()

        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        gradlePluginPortal()
    }

    val group = "io.github.merseyside"
    val catalogVersions = "1.6.8"
    versionCatalogs {
        val catalogGradle by creating {
            from("$group:catalog-version-gradle:$catalogVersions")
        }
    }
}