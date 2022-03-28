import com.merseyside.gradle.plugin.android.Theme.*

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    with(catalogPlugins.plugins) {
        plugin(android.application)
        plugin(kotlin.android)
        plugin(mersey.kotlin.convention)
        plugin(mersey.android.convention)
        plugin(swiftPackage)
    }
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}

androidConvention {
    sourceSets {
        setSourceSets = true

        configureThemes {
            name = "Theme.App"
            themes = listOf(LIGHT, NIGHT)
        }
    }

    debug = true
}

kotlinConvention {
    jvmTarget = JavaVersion.VERSION_11
}

dependencies {
    with(androidLibs) {
        implementation(appCompat)
        implementation(material)
    }
}