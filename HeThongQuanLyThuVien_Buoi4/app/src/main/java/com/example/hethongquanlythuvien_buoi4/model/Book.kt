package com.example.hethongquanlythuvien_buoi4.model

class Book(
    private val id: String,
    title: String,
    private val author: String,
    private val entryDate: String = ""
) {
    private var title: String = title
    private var borrowed: Boolean = false

    fun getId(): String = id
    fun getTitle(): String = title
    fun getAuthor(): String = author
    fun getEntryDate(): String = entryDate
    fun isBorrowed(): Boolean = borrowed

    fun setTitle(newTitle: String) {
        if (newTitle.isNotBlank()) title = newTitle
    }

    fun borrow(): Boolean {
        if (borrowed) return false
        borrowed = true
        return true
    }

    fun returnBook() {
        borrowed = false
    }
}
