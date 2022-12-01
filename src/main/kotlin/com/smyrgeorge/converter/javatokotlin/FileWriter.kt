package com.smyrgeorge.converter.javatokotlin

import com.smyrgeorge.converter.javatokotlin.types.ClassDef
import java.io.File

object FileWriter {

    private fun file(path: String, fileName: String): File = File("$path/$fileName")
    private fun fileName(aClass: ClassDef): String = "${aClass.name}.kt"
    private fun content(packageName: String, aClass: ClassDef): String = "package $packageName\n\n${aClass}"

    fun write(path: String, packageName: String, classes: List<ClassDef>) {
        // Create folders.
        File(path).mkdirs()
        // Write files.
        classes.forEach { write(path, packageName, it) }
    }

    private fun write(path: String, packageName: String, aClass: ClassDef) =
        file(path, fileName(aClass)).writeText(content(packageName, aClass))

}