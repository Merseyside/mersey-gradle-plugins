import com.merseyside.gradle.plugin.android.Theme.*

plugins {
    with(catalogPlugins.plugins) {
        plugin(android.application)
        plugin(kotlin.android)
        plugin(mersey.kotlin.extension)
        plugin(mersey.android.extension)
        plugin(swiftPackage)
    }
}

android {
    namespace = "io.github.merseyside"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
}

androidExtension {
    sourceSets {
        //setSourceSets = true

        configureThemes {
            name = "Theme.App"
            themes = listOf(LIGHT, NIGHT)
        }
    }

    debug = true
}

kotlinExtension {
    jvmTarget = JavaVersion.VERSION_17
}

dependencies {
    with(androidLibs) {
        implementation(appCompat)
        implementation(material)
    }
}