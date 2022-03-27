package com.merseyside.gradle.extensions

import com.merseyside.gradle.androidIds
import com.merseyside.gradle.kotlinIds
import org.gradle.api.plugins.PluginManager

internal fun PluginManager.hasKotlinPlugins(): Boolean {
    return kotlinIds.any { hasPlugin(it) }
}

internal fun PluginManager.hasAndroidPlugins(): Boolean {
    return androidIds.any { hasPlugin(it) }
}