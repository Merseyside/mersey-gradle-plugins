package com.merseyside.gradle

import com.android.build.gradle.internal.utils.KOTLIN_ANDROID_PLUGIN_ID
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
    "src/main/res/value/values-light",
    "src/main/res/value/values-night"
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