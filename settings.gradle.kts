enableFeaturePreview("VERSION_CATALOGS")

includeBuild("build-logic")
includeBuild("plugins")
include(":sample")

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
    }
}

rootProject.name = "mersey-gradle-plugins"

