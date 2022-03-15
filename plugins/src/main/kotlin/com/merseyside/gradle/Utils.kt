package com.merseyside.gradle

internal fun Any.log(tag: String = this::class.simpleName ?: "Logger") {
    println("$tag: ${toString()}")
}

internal fun LoggerExtension.printLog(msg: String) {
    if (debug) println("$TAG: $msg")
}