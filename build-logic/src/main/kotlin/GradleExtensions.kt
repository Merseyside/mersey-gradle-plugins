import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

fun Provider<PluginDependency>.id(): String {
    //println(get().version)
    return get().pluginId
}