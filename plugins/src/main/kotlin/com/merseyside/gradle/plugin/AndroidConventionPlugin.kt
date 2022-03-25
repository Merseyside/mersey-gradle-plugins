package com.merseyside.gradle.plugin

import com.android.build.api.dsl.LibraryExtension
import com.merseyside.gradle.*
import com.merseyside.gradle.androidIds
import com.merseyside.gradle.defaultMetadata
import com.merseyside.gradle.defaultSourceSets
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.findByType
import java.io.File

@Suppress("UnstableApiUsage")
class AndroidConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        if (!project.pluginManager.hasAndroidPlugins()) {
            throw ExceptionInInitializerError("No id from ${androidIds.joinToString(", ")} found!")
        }

        val androidLibraryExtension = project.extensions.findByType(LibraryExtension::class)

        val androidConventionExtension = project.extensions.create(
            "androidConvention",
            AndroidConventionPluginExtension::class
        )

        if (androidLibraryExtension != null) {
            setCompatibilityTarget(androidLibraryExtension, androidConventionExtension)
            excludeMetadata(androidLibraryExtension, androidConventionExtension)
            project.afterEvaluate {
                setSourceSets(project, androidLibraryExtension, androidConventionExtension)
            }
        }
    }

    private fun setSourceSets(
        project: Project,
        androidLibraryExtension: LibraryExtension,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        if (androidConventionPluginExtension.setSourceSets) {
            with(androidConventionPluginExtension) {
                androidLibraryExtension.sourceSets.apply {
                    if (isNotEmpty()) {
                        getByName("main") {
                            it.res.setSrcDirs(mainSourceSets)
                        }

                        createSourceSetFolders(
                            project,
                            androidConventionPluginExtension
                        )
                    }
                }
            }
        }
    }

    private fun setCompatibilityTarget(
        androidLibraryExtension: LibraryExtension,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        androidLibraryExtension.compileOptions.apply {
            androidConventionPluginExtension.printLog(
                "Set " +
                        "${androidConventionPluginExtension.javaVersion} compatibility target"
            )

            sourceCompatibility = androidConventionPluginExtension.javaVersion
            targetCompatibility = androidConventionPluginExtension.javaVersion
        }
    }

    private fun excludeMetadata(
        androidLibraryExtension: LibraryExtension,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        with(androidConventionPluginExtension) {
            if (excludeMetadata) {
                printLog("\nExclude ${excludeMetadataSet.joinToString(",\n")}\nmetadata")

                androidLibraryExtension.packagingOptions.resources.excludes.addAll(
                    excludeMetadataSet
                )
            }
        }
    }

    private fun createSourceSetFolders(
        project: Project,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        val sourceSetFiles = androidConventionPluginExtension.mainSourceSets.map { sourceSet ->
            project.file(sourceSet)
        }

        sourceSetFiles.forEach { file ->
            file as File
            val path = file.absolutePath
            val newFile = if (file.parent.contains("layouts")) {
                File("$path/layout")
            } else {
                file
            }

            if (!newFile.exists()) {
                project.logger.info("Create ${file.absoluteFile} folder")
                newFile.mkdirs()
            }
        }
    }
}

open class AndroidConventionPluginExtension : LoggerExtension {
    var javaVersion: JavaVersion = JavaVersion.VERSION_11
    var setSourceSets: Boolean = false
    var mainSourceSets: MutableSet<String> = defaultSourceSets
    var excludeMetadata: Boolean = false
    var excludeMetadataSet: MutableSet<String> = defaultMetadata

    override var debug: Boolean = true
    override val TAG: String = "AndroidConventionPlugin"
}