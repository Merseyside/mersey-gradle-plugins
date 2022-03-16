package com.merseyside.gradle

import org.gradle.api.plugins.PluginManager

internal fun PluginManager.hasKotlinPlugins(): Boolean {
    return kotlinIds.any { hasPlugin(it) }
}

internal fun PluginManager.hasAndroidPlugins(): Boolean {
    return androidIds.any { hasPlugin(it) }
}