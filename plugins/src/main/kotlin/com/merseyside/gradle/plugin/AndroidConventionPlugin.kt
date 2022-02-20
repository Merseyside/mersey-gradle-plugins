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

class AndroidConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        if (!target.pluginManager.hasAndroidPlugins()) {
            throw ExceptionInInitializerError("No id from ${androidIds.joinToString(", ")} found!")
        }

        val androidLibraryExtension = target.extensions.findByType(LibraryExtension::class)

        val androidConventionExtension = target.extensions.create(
            "androidConvention",
            AndroidConventionPluginExtension::class
        )

        target.afterEvaluate {
            if (androidLibraryExtension != null) {
                setCompatibilityTarget(androidLibraryExtension, androidConventionExtension)
                setSourceSets(androidLibraryExtension, androidConventionExtension)
                excludeMetadata(androidLibraryExtension, androidConventionExtension)
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

    private fun setSourceSets(
        androidLibraryExtension: LibraryExtension,
        androidConventionPluginExtension: AndroidConventionPluginExtension
    ) {
        with(androidConventionPluginExtension) {
            androidLibraryExtension.sourceSets.apply {
                getByName("main") {
                    printLog(
                        "Set ${sourceSets.joinToString(",\n")}" +
                                "\nto main source set"
                    )

                    it.res.setSrcDirs(sourceSets)
                }
            }
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
}

open class AndroidConventionPluginExtension : LoggerExtension {
    var javaVersion: JavaVersion = JavaVersion.VERSION_11
    var sourceSets: MutableSet<String> = defaultSourceSets
    var excludeMetadata: Boolean = false
    var excludeMetadataSet: MutableSet<String> = defaultMetadata

    override var debug: Boolean = false
    override val TAG: String = "AndroidConventionPlugin"
}