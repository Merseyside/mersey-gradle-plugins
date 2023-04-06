pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    includeBuild("build-logic")
    includeBuild("plugins")
}

plugins {
    // Activates ".id()" extension
    id("buildscript-extensions")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
    }

    val group = "io.github.merseyside"
    val catalogVersions = "1.7.0"
    versionCatalogs {
        val androidLibs by creating {
            from("$group:catalog-version-android:$catalogVersions")
        }

        val catalogPlugins by creating {
            from("$group:catalog-version-plugins:$catalogVersions")
            version("pluginPublish", "1.1.0")
        }
    }
}

include(":sample")

rootProject.name = "mersey-gradle-plugins"

