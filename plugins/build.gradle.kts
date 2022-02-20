plugins {
    id("gradle-plugin-convention")
    id("com.gradle.plugin-publish") version ("0.15.0")
}

group = "com.merseyside.mobile"
version = "1.0.0"

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly(libs.kotlinGradlePlugin)
    compileOnly(libs.androidGradlePlugin)
}

gradlePlugin {
    plugins {
        create("kotlin-convention") {
            id = "com.merseyside.mobile.kotlin-convention"
            implementationClass = "com.merseyside.gradle.plugin.KotlinConventionPlugin"
        }

        create("android-convention") {
            id = "com.merseyside.mobile.android-convention"
            implementationClass = "com.merseyside.gradle.plugin.AndroidConventionPlugin"
        }
    }
}