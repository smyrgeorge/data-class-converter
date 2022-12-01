package com.smyrgeorge.converter.javatokotlin

import com.smyrgeorge.converter.javatokotlin.types.ClassDef
import com.smyrgeorge.converter.javatokotlin.types.field.EnumFieldDef

object JavaEnumClassToKotlinEnumClass : Converter, FieldConverter {

    override fun Class<*>.toFieldDefList(): List<EnumFieldDef> =
        declaredFields.filter { !it.name.startsWith("$") }.map { EnumFieldDef(name = it.name) }

    override fun convert(packageName: String): List<ClassDef> =
        error("This method is not supported by the current converter.")

    override fun convert(aClass: Class<*>): ClassDef =
        ClassDef(name = aClass.simpleName, isEnum = true, fields = aClass.toFieldDefList())

}