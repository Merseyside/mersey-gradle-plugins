@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("kotlin-android")
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
    javaVersion = JavaVersion.VERSION_11
    excludeMetadata = true
    debug = true
}

kotlinConvention {
    jvmTarget = JavaVersion.VERSION_11
}

dependencies {

    with(catalogPlugins.plugins) {
        androidLibrary.id()
    }
    with(androidLibs) {
        implementation(appCompat)
        implementation(material)
    }
}