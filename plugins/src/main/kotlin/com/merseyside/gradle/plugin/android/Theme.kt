package com.merseyside.gradle.plugin.android

sealed class Theme(val name: String) {
    object LIGHT: Theme("light")
    object NIGHT: Theme("night")
}