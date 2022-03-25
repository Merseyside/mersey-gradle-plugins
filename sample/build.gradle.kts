@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    with(catalogPlugins.plugins) {
        id(android.library.id())
        id(kotlin.android.id())
        id(mersey.kotlin.convention.id())
        id(mersey.android.convention.id())
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
    setSourceSets = true
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