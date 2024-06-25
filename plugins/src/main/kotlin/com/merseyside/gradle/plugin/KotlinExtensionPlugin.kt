package com.merseyside.gradle.plugin

import com.merseyside.gradle.LoggerExtension
import com.merseyside.gradle.extensions.hasKotlinPlugins
import com.merseyside.gradle.kotlinIds
import com.merseyside.gradle.printLog
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.api.publish.tasks.GenerateModuleMetadata
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinExtensionPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        if (!target.pluginManager.hasKotlinPlugins()) throw ExceptionInInitializerError(
            "No id from (${kotlinIds.joinToString(", ")}) was found!"
        )

        val kotlinExtensionPluginExtension = target.extensions.create(
            "kotlinExtension",
            KotlinExtensionPluginExtension::class
        )

        target.afterEvaluate {
            configureProject(target, kotlinExtensionPluginExtension)
        }
    }

    private fun configureProject(
        target: Project,
        kotlinExtensionExtension: KotlinExtensionPluginExtension
    ) {
        with(kotlinExtensionExtension) {
            printLog("Set ${kotlinExtensionExtension.jvmTarget} jvmTarget")
            if (compilerArgs.isNotEmpty()) printLog(
                "Add ${compilerArgs.joinToString(", ")} compiler options"
            )

            target.tasks.withType(KotlinCompile::class) {
                it.kotlinOptions.apply {
                    jvmTarget = this@with.jvmTarget.toString()
                    freeCompilerArgs = compilerArgs
                }
            }

            if (disableCrossCompilation) {
                if (target.pluginManager.hasPlugin("kotlin-multiplatform")
                    || target.pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform")
                ) {

                    target.plugins.withType(KotlinMultiplatformPluginWrapper::class) {
                        val multiplatformExtension =
                            target.extensions.getByType(KotlinMultiplatformExtension::class)

                        disableCrossCompilation(target, multiplatformExtension)
                    }
                }
            }
        }
    }

    private fun disableCrossCompilation(
        project: Project,
        kotlinMultiplatformExtension: KotlinMultiplatformExtension
    ): Boolean {
        with(project) {
            with(kotlinMultiplatformExtension) {
                val os = OperatingSystem.current()
                val excludeTargets = when {
                    !os.isMacOsX -> targets.filter { it.name.contains("ios") }
                    else -> emptyList()
                }.mapNotNull { it as? KotlinNativeTarget }

                val message = if (excludeTargets.isNotEmpty()) {
                    excludeTargets.joinToString(separator = ",", prefix = "Excluding targets: ")
                } else {
                    "No targets are going to be excluded..."
                }

                println(message)

                configure(excludeTargets) { exclude ->
                    exclude.compilations.all { compilation ->
                        compilation.cinterops.all { cinterop ->
                            tasks[cinterop.interopProcessingTaskName].enabled = false
                        }
                        compilation.compileTaskProvider.get().enabled = false
                        tasks[compilation.processResourcesTaskName].enabled = false
                    }
                    exclude.binaries.all { binary -> binary.linkTask.enabled = false }

                    exclude.mavenPublication {
                        val publicationToDisable = this
                        tasks.withType<AbstractPublishToMaven>()
                            .all { task -> task.onlyIf { task.publication != publicationToDisable } }
                        tasks.withType<GenerateModuleMetadata>()
                            .all { task -> task.onlyIf { task.publication.get() != publicationToDisable } }
                    }
                }

                return excludeTargets.isNotEmpty()
            }
        }
    }
}

open class KotlinExtensionPluginExtension : LoggerExtension {
    var jvmTarget: JavaVersion = JavaVersion.VERSION_21
    var compilerArgs: List<String> = emptyList()
    var disableCrossCompilation: Boolean = true

    fun setCompilerArgs(vararg args: String) {
        compilerArgs = args.toList()
    }

    override var debug: Boolean = false
    override val TAG: String = "KotlinExtensionPlugin"
}