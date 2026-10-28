
package com.example.hethongquanlythuvien_buoi4.model

class Student(
    id: String,
    name: String,
    private val studentCode: String
) : Person(id, name) {

    override fun getInfo(): String {
        return "Sinh viên: ${getPersonName()} - MSSV: $studentCode"
    }

    fun getStudentCode(): String = studentCode
}
