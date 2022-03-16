@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(catalogPlugins.plugins.androidLibrary.id())
    id(catalogPlugins.plugins.kotlinAndroid.id())
    id("com.merseyside.mobile.kotlin-convention")
    id("com.merseyside.mobile.android-convention")
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