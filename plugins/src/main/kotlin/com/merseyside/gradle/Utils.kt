package com.merseyside.gradle

fun LoggerExtension.printLog(msg: String) {
    if (debug) println("$TAG: $msg")
}