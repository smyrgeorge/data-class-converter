package com.smyrgeorge.converter.javatokotlin.types

import com.smyrgeorge.converter.javatokotlin.types.field.FieldDef

data class ClassDef(
    val name: String,
    val isEnum: Boolean,
    val fields: List<FieldDef>
) {

    private fun fields(): String = "    " + fields.joinToString(",\n    ")
    private fun enumToString(): String = "enum class $name {\n${fields()}\n}"
    private fun genericToString(): String = "data class $name(\n${fields()}\n)"

    override fun toString(): String = when {
        isEnum -> enumToString()
        else -> genericToString()
    }

}