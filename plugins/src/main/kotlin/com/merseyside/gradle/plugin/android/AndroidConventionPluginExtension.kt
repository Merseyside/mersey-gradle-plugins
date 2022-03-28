package com.merseyside.gradle.plugin.android

import com.merseyside.gradle.defaultMetadata
import com.merseyside.gradle.defaultSourceSets
import org.gradle.api.JavaVersion

open class AndroidConventionPluginExtension {
    var javaVersion: JavaVersion = JavaVersion.VERSION_11
    var excludeMetadata: Boolean = false
    var excludeMetadataSet: MutableSet<String> = defaultMetadata
    internal val sourceSet: SourceSet = SourceSet()

    fun sourceSets(configure: SourceSet.() -> Unit) {
        this.sourceSet.configure()
    }

    var debug: Boolean = true
}

class SourceSet(
    var setSourceSets: Boolean = false,
    var mainSourceSets: MutableSet<String> = defaultSourceSets,
    internal var themes: Themes? = null
) {
    fun configureThemes(configure: Themes.() -> Unit) {
        themes = Themes().apply {
            configure()

            name.ifEmpty {
                throw IllegalArgumentException("Name can not be empty")
            }

            themes.ifEmpty {
                throw IllegalArgumentException("Themes can not be empty")
            }
        }
    }
}

class Themes(
    var themes: List<Theme> = listOf(),
    var name: String = ""
)