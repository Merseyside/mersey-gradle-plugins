package com.merseyside.gradle

import org.gradle.api.Project
import java.io.File

internal fun Any.log(tag: String = this::class.simpleName ?: "Logger") {
    println("$tag: ${toString()}")
}

internal fun LoggerExtension.printLog(msg: String) {
    if (debug) println("$TAG: $msg")
}

fun createFolder(project: Project, folderPath: String): File {
    val newFolder = project.file(folderPath)
    if (!newFolder.exists()) {
        project.logger.info("Create ${newFolder.absoluteFile} folder")

        newFolder.mkdirs()
    }

    return newFolder
}

fun createFolders(project: Project, folderPaths: List<String>): List<File> {
    return folderPaths.map { filePath -> createFolder(project, filePath) }
}

fun createFile(project: Project, folderPath: String, fileName: String): File? {
    val folder = createFolder(project, folderPath)
    val newFile = File(folder, fileName)
    return if (newFile.createNewFile()) {
        newFile
    } else {
        null
    }
}

fun getParentPath(path: String): String {
    return path.substringBeforeLast("/", missingDelimiterValue = "")
}

fun getFolder(path: String): String {
    return path.substringAfterLast("/", missingDelimiterValue = "")
}

fun getParentFolder(path: String): String {
    return getFolder(getParentPath(path))
}

