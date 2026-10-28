package com.example.hethongquanlythuvien_buoi4.model

class Librarian(
    id: String,
    name: String,
    private val employeeCode: String
) : Person(id, name) {

    override fun getInfo(): String {
        return "Thủ thư: ${getPersonName()} - Mã nhân viên: $employeeCode"
    }
}
