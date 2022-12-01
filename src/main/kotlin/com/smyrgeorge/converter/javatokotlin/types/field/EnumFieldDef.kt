package com.smyrgeorge.converter.javatokotlin.types.field

data class EnumFieldDef(
    override val name: String
) : FieldDef() {
    override fun toString(): String = name
}