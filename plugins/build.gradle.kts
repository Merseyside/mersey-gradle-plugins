@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(catalogPlugins.plugins.pluginPublish)
    id("gradle-plugin-extension")
    id("maven-publish-config")
    id("nexus-config")
    id("java-gradle-plugin")
}

group = "io.github.merseyside"
version = catalogPlugins.versions.mersey.plugins.get()

dependencies {
    implementation(gradleKotlinDsl())
    compileOnly(catalogGradle.kotlin.gradle)
    compileOnly(catalogGradle.android.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()
}

publishing.publications.register("mavenJava", MavenPublication::class) {
    from(components["java"])
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
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
    vcsUrl.set("https://github.com/Merseyside/mersey-gradle-plugins")

    plugins {
        getByName("kotlin-extension") {
            displayName = "Mersey kotlin extension plugin"
        }

        getByName("android-extension") {
            displayName = "Mersey android extension plugin"
        }
    }

//    mavenCoordinates {
//        groupId = project.group as String
//        artifactId = project.name
//        version = project.version as String
//    }
}