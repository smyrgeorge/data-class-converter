package com.smyrgeorge.converter.javatokotlin

import com.smyrgeorge.converter.javatokotlin.types.ClassDef
import com.smyrgeorge.converter.javatokotlin.types.field.GenericFieldDef
import java.lang.reflect.Field

object JavaGenericClassToKotlinDataClass : Converter, FieldConverter {

    // Used to convert types from one type to another.
    private val types: Map<String, String> = mapOf(
        "int" to "Int",
        "float" to "Float",
        "long" to "Long",
        "double" to "Double",
        "boolean" to "Boolean"
    )

    // Finds the type of the [Field].
    // Also, it tries to utilize the type mappings ([types] map).
    private fun Field.type(): String = types[type.simpleName] ?: type.simpleName

    // Checks for [NotNull] annotation (only checks that an annotation with the appropriate name exists).
    private fun Field.isNullable(): Boolean =
        !type.isPrimitive && annotatedType.annotations.find { it.annotationClass.simpleName == "NotNull" } == null

    override fun Class<*>.toFieldDefList(): List<GenericFieldDef> =
        declaredFields.map { GenericFieldDef(name = it.name, type = it.type(), nullable = it.isNullable()) }

    override fun convert(packageName: String): List<ClassDef> =
        error("This method is not supported by the current converter.")

    override fun convert(aClass: Class<*>): ClassDef =
        ClassDef(name = aClass.simpleName, isEnum = false, fields = aClass.toFieldDefList())
}