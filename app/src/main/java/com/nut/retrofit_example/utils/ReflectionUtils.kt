package com.nut.retrofit_example.utils

internal fun Any.fieldsToMap(): Map<String, Any?> {
    val cls = this::class.java
    val fields = cls.declaredFields
    return fields
        .map { field -> field.name to this.getFieldValue(field.name) }
        .toMap()
}

private fun Any.getFieldValue(name: String): Any? {
    val getterName = getterNameForFieldName(name)
    return this::class.java.declaredMethods
        .first { it.name.contains(getterName) }
        .invoke(this)
}

private fun getterNameForFieldName(name: String): String {
    return "get" + name.take(1).uppercase() + name.drop(1)
}
