import com.merseyside.gradle.plugin.android.Theme.*

@Suppress("DSL_SCOPE_VIOLATION")
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
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}

androidExtension {
    sourceSets {
        setSourceSets = true

        configureThemes {
            name = "Theme.App"
            themes = listOf(LIGHT, NIGHT)
        }
    }

    debug = true
}

kotlinExtension {
    jvmTarget = JavaVersion.VERSION_11
}

dependencies {
    with(androidLibs) {
        implementation(appCompat)
        implementation(material)
    }
}