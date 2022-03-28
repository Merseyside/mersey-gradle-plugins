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
            id = "${Metadata.groupId}.kotlin-convention"
            implementationClass = "com.merseyside.gradle.plugin.KotlinConventionPlugin"
        }

        create("android-convention") {
            id = "${Metadata.groupId}.android-convention"
            implementationClass = "com.merseyside.gradle.plugin.android.AndroidConventionPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/Merseyside/mersey-gradle-plugins"
    vcsUrl = "https://github.com/Merseyside/mersey-gradle-plugins"
    description = "Plugin to optimize work with kotlin/android conventions and useful features"
    tags = listOf("android", "mersey", "kotlin", "sourceSets")

    plugins {
        getByName("kotlin-convention") {
            displayName = "Mersey kotlin convention plugin"
        }

        getByName("android-convention") {
            displayName = "Mersey android convention plugin"
        }
    }

    mavenCoordinates {
        groupId = Metadata.groupId
        artifactId = project.name
        version = Metadata.version
    }
}