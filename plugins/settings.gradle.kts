enableFeaturePreview("VERSION_CATALOGS")

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
    }

    versionCatalogs {
        val group = "io.github.merseyside"
        val catalogVersions = "1.2.9"
        versionCatalogs {
            val androidLibs by creating {
                from("$group:catalog-version-android:$catalogVersions")
            }
        }

        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

includeBuild("../build-logic")

