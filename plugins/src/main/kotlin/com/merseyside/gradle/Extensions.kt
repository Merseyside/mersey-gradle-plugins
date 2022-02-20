package com.merseyside.gradle

import org.gradle.api.plugins.PluginManager

fun PluginManager.hasKotlinPlugins(): Boolean {
    return kotlinIds.any { hasPlugin(it) }
}

fun PluginManager.hasAndroidPlugins(): Boolean {
    return androidIds.any { hasPlugin(it) }
}