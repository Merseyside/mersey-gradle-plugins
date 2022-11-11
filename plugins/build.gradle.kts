@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(catalogPlugins.plugins.pluginPublish)
    id("gradle-plugin-extension")
    id("maven-publish-config")
    id("nexus-config")
}

group = "io.github.merseyside"
version = catalogPlugins.versions.merseyPlugins.get()

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
        }

        create("android-extension") {
            id = "${project.group}.android-extension"
            implementationClass = "com.merseyside.gradle.plugin.android.AndroidExtensionPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/Merseyside/mersey-gradle-plugins"
    vcsUrl = "https://github.com/Merseyside/mersey-gradle-plugins"
    description = "Plugin to optimize work with kotlin/android extensions and useful features"
    tags = listOf("android", "mersey", "kotlin", "sourceSets")

    plugins {
        getByName("kotlin-extension") {
            displayName = "Mersey kotlin extension plugin"
        }

        getByName("android-extension") {
            displayName = "Mersey android extension plugin"
        }
    }

    mavenCoordinates {
        groupId = project.group as String
        artifactId = project.name
        version = project.version as String
    }
}