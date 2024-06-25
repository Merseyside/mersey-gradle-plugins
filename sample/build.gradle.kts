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

kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

androidExtension {
    javaVersion = JavaVersion.VERSION_21
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
    jvmTarget = JavaVersion.VERSION_21
}

dependencies {
    with(androidLibs) {
        implementation(appCompat)
        implementation(material)
    }
}