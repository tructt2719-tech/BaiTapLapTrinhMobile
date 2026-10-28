
package com.example.hethongquanlythuvien_buoi4.model

abstract class Person(
    private val id: String,
    initialName: String
) {
    private var name: String = initialName

    fun getPersonId(): String = id

    fun getPersonName(): String = name

    fun updateName(newName: String) {
        if (newName.isNotBlank()) {
            name = newName
        }
    }

    abstract fun getInfo(): String
}
