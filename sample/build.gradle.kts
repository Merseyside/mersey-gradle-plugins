@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("io.github.merseyside.kotlin-convention")
    id("io.github.merseyside.android-convention")
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