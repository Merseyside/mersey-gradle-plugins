package com.merseyside.gradle

import com.android.build.gradle.internal.utils.KOTLIN_ANDROID_PLUGIN_ID
import com.merseyside.gradle.plugin.android.Theme
import org.jetbrains.kotlin.gradle.plugin.KOTLIN_DSL_NAME

internal val kotlinIds = listOf(
    KOTLIN_DSL_NAME,
    KOTLIN_ANDROID_PLUGIN_ID,
    "kotlin-multiplatform",
    "org.jetbrains.kotlin.multiplatform"
)

internal val androidIds = listOf(
    "com.android.application",
    "com.android.library"
)

internal val defaultSourceSets = mutableSetOf(
    "src/main/res/",
    "src/main/res/layouts/fragment",
    "src/main/res/layouts/activity",
    "src/main/res/layouts/dialog",
    "src/main/res/layouts/view",
    "src/main/res/values"
)

internal val defaultMetadata = mutableSetOf(
    "META-INF/DEPENDENCIES",
    "META-INF/LICENSE",
    "META-INF/LICENSE.txt",
    "META-INF/license.txt",
    "META-INF/NOTICE",
    "META-INF/NOTICE.txt",
    "META-INF/notice.txt",
    "META-INF/ASL2.0",
    "META-INF/*.kotlin_module"
)

fun getThemeSourceSet(theme: Theme): String {
    return "src/main/res/values/values-${theme.name}"
}

fun getThemeFolders(theme: Theme): List<String> {
    val parent = getThemeSourceSet(theme)
    return listOf(
        "$parent/values",
        "$parent/values-v21",
        "$parent/values-v23",
        "$parent/values-v27"
    )
}