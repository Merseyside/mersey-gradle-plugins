package com.merseyside.gradle.plugin.android

import com.android.build.api.dsl.CommonExtension
import com.merseyside.gradle.*
import com.merseyside.gradle.androidIds
import com.merseyside.gradle.extensions.hasAndroidPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.findByType

@Suppress("UnstableApiUsage")
class AndroidConventionPlugin : Plugin<Project>, LoggerExtension {

    override fun apply(project: Project) {

        if (!project.pluginManager.hasAndroidPlugins()) {
            throw ExceptionInInitializerError("No id from ${androidIds.joinToString(", ")} found!")
        }

        val androidExtension = project.extensions.findByType(CommonExtension::class)

        val androidConventionExtension = project.extensions.create(
            "androidConvention",
            AndroidConventionPluginExtension::class
        ).also { debug = it.debug }

        if (androidExtension != null) {
            setCompatibilityTarget(androidExtension, androidConventionExtension)
            excludeMetadata(androidExtension, androidConventionExtension)
            project.afterEvaluate {
                setSourceSets(project, androidExtension, androidConventionExtension.sourceSet)
            }
        }
    }

    private fun setSourceSets(
        project: Project,
        androidExtension: AndroidExtension,
        sourceSets: SourceSet
    ) {
        if (sourceSets.setSourceSets) {
            with(sourceSets) {
                androidExtension.sourceSets.apply {
                    if (isNotEmpty()) {
                        getByName("main") {
                            printLog("Set source sets")
                            it.res.setSrcDirs(mainSourceSets)
                        }

                        createSourceSetFolders(project, sourceSets)
                    }

                    themes?.let {
                        val themeSourceSets = it.themes.map { theme -> getThemeSourceSet(theme) }
                        getByName("main") {
                            it.res.srcDirs(themeSourceSets)
                        }

                        createThemesFiles(project, it)
                    }
                }
            }
        }
    }

    private fun setCompatibilityTarget(
        androidLibraryExtension: AndroidExtension,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        androidLibraryExtension.compileOptions.apply {
            printLog("Set " +
                        "${androidConventionPluginExtension.javaVersion} compatibility target")

            sourceCompatibility = androidConventionPluginExtension.javaVersion
            targetCompatibility = androidConventionPluginExtension.javaVersion
        }
    }

    private fun excludeMetadata(
        androidExtension: AndroidExtension,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        with(androidConventionPluginExtension) {
            if (excludeMetadata) {
                printLog("\nExclude ${excludeMetadataSet.joinToString(",\n")}\nmetadata")

                androidExtension.packagingOptions.resources.excludes.addAll(
                    excludeMetadataSet
                )
            }
        }
    }

    private fun createSourceSetFolders(
        project: Project,
        sourceSets: SourceSet
    ) {
        val sourceSetFiles = sourceSets.mainSourceSets.map { sourceSet ->
            if (getParentFolder(sourceSet) == "layouts") {
                "$sourceSet/layout"
            } else {
                sourceSet
            }
        }

        createFolders(project, sourceSetFiles)
    }

    private fun createThemesFiles(
        project: Project,
        themes: Themes
    ) {
        themes.themes.forEach { theme ->
            val paths = getThemeFolders(theme)
            val createdStyles = paths.mapNotNull { folderPath ->
                createFile(project, folderPath, "styles.xml")
            }
        }
    }

    override var debug: Boolean = false
    override val TAG: String = "AndroidConventionPlugin"
}