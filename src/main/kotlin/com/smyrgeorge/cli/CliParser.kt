package com.smyrgeorge.cli

import org.apache.commons.cli.*
import kotlin.system.exitProcess

object CliParser {

    fun parse(args: Array<String>): CliOptions {
        val options = Options()

        val scanPackage = Option(
            "sp",
            "scan-package",
            true,
            "Specifies the search path. All classes in this package will be transformed."
        ).apply { isRequired = true }
        options.addOption(scanPackage)

        val packageName = Option(
            "pn",
            "package-name",
            true,
            "Specifies the package name of the all generated classes."
        ).apply { isRequired = true }
        options.addOption(packageName)

        val outputPath = Option(
            "op",
            "output-path",
            true,
            "Specifies the output directory path."
        ).apply { isRequired = true }
        options.addOption(outputPath)

        return try {
            val cmd: CommandLine = DefaultParser().parse(options, args)
            CliOptions(
                scanPackage = cmd.getOptionValue("p"),
                packageName = cmd.getOptionValue("pn"),
                outputPath = cmd.getOptionValue("op")
            )
        } catch (e: ParseException) {
            println(e.message)
            HelpFormatter().printHelp("java -jar java-to-kotlin", options)
            exitProcess(1)
        }
    }

}