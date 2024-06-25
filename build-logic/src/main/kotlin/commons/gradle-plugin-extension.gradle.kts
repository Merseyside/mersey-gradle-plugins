import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.`java-gradle-plugin`

plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-gradle-plugin`
}

kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

