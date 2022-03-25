import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependency
import org.gradle.plugin.use.PluginDependencySpec

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