@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependency
import org.gradle.plugin.use.PluginDependencySpec

inline fun <reified T> Project.findTypedProperty(propertyName: String): T {
    val stringProperty = findProperty(propertyName) as? String

    return stringProperty?.let {
        castProperty(it)
    } ?: throw Exception("Property $propertyName not found")
}

inline fun <reified T> Project.findTypedLocalProperty(propertyName: String): T {
    val stringProperty = gradleLocalProperties(rootDir)[propertyName] as? String

    return stringProperty?.let {
        castProperty(it)
    } ?: throw Exception("Property $propertyName not found")
}

inline fun <reified T> castProperty(stringProperty: String): T {
    val value = when (T::class) {
        Boolean::class -> stringProperty.toBoolean()
        Int::class -> stringProperty.toInt()
        Float::class -> stringProperty.toFloat()
        else -> stringProperty
    }

    return value as T
}

fun Project.isLocalDependencies(): Boolean =
    findTypedProperty("build.localDependencies")

fun Project.isLocalAndroidDependencies(): Boolean =
    findTypedProperty("build.localAndroidDependencies")

fun Project.isLocalKotlinExtLibrary(): Boolean =
    findTypedProperty("build.localKotlinExtLibrary")

fun Project.isBuildIos(): Boolean =
    findTypedProperty("build.buildIos")

fun PluginDependenciesSpec.plugin(gradlePlugin: Provider<PluginDependency>): PluginDependencySpec {
    val spec = id(gradlePlugin.get().pluginId)
    val version = gradlePlugin.get().version.requiredVersion

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