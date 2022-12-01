package com.smyrgeorge.converter.javatokotlin

import com.smyrgeorge.converter.javatokotlin.types.field.FieldDef

interface FieldConverter {
    // Converts all class field members to a [List] of [FieldDef].
    fun Class<*>.toFieldDefList(): List<FieldDef>
}