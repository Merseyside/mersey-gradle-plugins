/**
 * Precompiled [gradle-plugin-convention.gradle.kts][Gradle_plugin_convention_gradle] script plugin.
 *
 * @see Gradle_plugin_convention_gradle
 */
class GradlePluginConventionPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Gradle_plugin_convention_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
