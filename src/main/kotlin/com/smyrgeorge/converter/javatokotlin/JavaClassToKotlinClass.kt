package com.smyrgeorge.converter.javatokotlin

import com.smyrgeorge.converter.javatokotlin.types.ClassDef
import org.reflections.Reflections
import org.reflections.scanners.Scanners

/**
 * [JavaClassToKotlinClass] converts a Java class to a Kotlin class.
 */
object JavaClassToKotlinClass : Converter {

    override fun convert(packageName: String): List<ClassDef> {
        val ref = Reflections(packageName, Scanners.SubTypes)
        val res = ref.getSubTypesOf(Enum::class.java) + ref.getSubTypesOf(Record::class.java)
        return res.map { convert(it) }
    }

    override fun convert(aClass: Class<*>): ClassDef =
        // Case enum class.
        if (aClass.isEnum) JavaEnumClassToKotlinEnumClass.convert(aClass)
        // All other cases.
        else JavaGenericClassToKotlinDataClass.convert(aClass)
}