plugins {
    alias(catalogPlugins.plugins.pluginPublish)
    `gradle-plugin-extension`
    `maven-publish-plugin`
}

group = "io.github.merseyside"
version = catalogGradle.versions.mersey.plugins.get()

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly(catalogGradle.kotlin.gradle)
    compileOnly(catalogGradle.android.gradle)
}

gradlePlugin {
    plugins {
        create("kotlin-extension") {
            id = "${project.group}.kotlin-extension"
            implementationClass = "com.merseyside.gradle.plugin.KotlinExtensionPlugin"
            tags.set(listOf("android", "mersey", "kotlin", "sourceSets"))
            description = "Plugin to optimize work with kotlin/android extensions and useful features"
        }

        create("android-extension") {
            id = "${project.group}.android-extension"
            implementationClass = "com.merseyside.gradle.plugin.android.AndroidExtensionPlugin"
            tags.set(listOf("android", "mersey", "kotlin", "sourceSets"))
            description = "Plugin to optimize work with kotlin/android extensions and useful features"
        }
    }

    website.set("https://github.com/Merseyside/mersey-gradle-plugins")
    vcsUrl.set("https://github.com/merseyside/mersey-gradle-plugins")

    plugins {
        getByName("kotlin-extension") {
            displayName = "Mersey kotlin extension plugin"
        }

        getByName("android-extension") {
            displayName = "Mersey android extension plugin"
        }
    }
}