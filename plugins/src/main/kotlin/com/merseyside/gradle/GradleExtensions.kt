@file:Suppress("UnstableApiUsage")

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependency
import org.gradle.plugin.use.PluginDependencySpec

inline fun <reified T> Project.findTypedProperty(propertyName: String): T {

    val stringProperty = findProperty(propertyName) as? String

    return stringProperty?.let {
        when (T::class) {
            Boolean::class -> stringProperty.toBoolean()
            Int::class -> stringProperty.toInt()
            Float::class -> stringProperty
            else -> it
        }
    } as? T ?: throw Exception("Property $propertyName not found")
}

fun Project.isLocalDependencies(): Boolean =
    findTypedProperty("build.localDependencies")

fun Project.isLocalAndroidDependencies(): Boolean =
    findTypedProperty("build.localAndroidDependencies")

fun Project.isLocalKotlinExtLibrary(): Boolean =
    findTypedProperty("build.localKotlinExtLibrary")

fun PluginDependenciesSpec.plugin(gradlePlugin: Provider<PluginDependency>): PluginDependencySpec {
    val spec = id(gradlePlugin.get().pluginId)
    val version = gradlePlugin.get().version.preferredVersion

    with(version) {
        if (isNotEmpty()) {
            spec.version(this)
        }
    }

    return spec
}

fun Provider<PluginDependency>.id(): String {
    return get().pluginId
}