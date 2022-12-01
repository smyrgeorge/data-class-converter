package com.smyrgeorge

import com.smyrgeorge.cli.CliParser
import com.smyrgeorge.converter.javatokotlin.FileWriter
import com.smyrgeorge.converter.javatokotlin.JavaClassToKotlinClass

/**
 * For developing purposes use the following:
 * --sp com.smyrgeorge.external
 *
 * --pn com.smyrgeorge.test.result
 * --op src/main/kotlin/com/smyrgeorge/external/generated
 */
fun main(args: Array<String>) {
    val options = CliParser.parse(args)
    val classes = JavaClassToKotlinClass.convert(options.scanPackage)
    FileWriter.write(options.outputPath, options.packageName, classes)
}
