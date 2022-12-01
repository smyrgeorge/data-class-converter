package com.smyrgeorge.converter.javatokotlin

import com.smyrgeorge.converter.javatokotlin.types.ClassDef

interface Converter {
    fun convert(packageName: String): List<ClassDef>
    fun convert(aClass: Class<*>): ClassDef
}