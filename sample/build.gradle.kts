plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.merseyside.mobile.kotlin-convention")
    id("com.merseyside.mobile.android-convention")
}

android {
    compileSdk = 31
}

androidConvention {
    javaVersion = JavaVersion.VERSION_11
    excludeMetadata = true
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