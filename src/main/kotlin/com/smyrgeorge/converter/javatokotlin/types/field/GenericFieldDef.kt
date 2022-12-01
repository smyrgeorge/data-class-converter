package com.smyrgeorge.converter.javatokotlin.types.field

data class GenericFieldDef(
    override val name: String,
    val type: String,
    val nullable: Boolean
) : FieldDef() {
    override fun toString(): String =
        "val $name: $type${if (nullable) "?" else ""}"
}