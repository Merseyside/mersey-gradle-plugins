@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(catalogPlugins.plugins.pluginPublish)
    id("gradle-plugin-convention")
    id("maven-publish-config")
    id("nexus-config")
}

group = Metadata.groupId
version = Metadata.version

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly(catalogGradle.kotlin.gradle)
    compileOnly(catalogGradle.android.gradle)
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