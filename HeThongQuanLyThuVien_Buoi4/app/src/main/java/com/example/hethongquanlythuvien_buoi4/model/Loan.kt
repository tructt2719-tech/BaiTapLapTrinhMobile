package com.example.hethongquanlythuvien_buoi4.model

import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Loan(
    private val student: Student,
    private val book: Book,
    private val borrowDate: String,
    private val dueDate: String
) {
    private var actualReturnDate: String? = null

    fun getStudent(): Student = student
    fun getBook(): Book = book
    fun getBorrowDate(): String = borrowDate
    fun getDueDate(): String = dueDate
    fun getActualReturnDate(): String? = actualReturnDate
    fun isActive(): Boolean = actualReturnDate == null

    fun borrowBook(): Boolean = book.borrow()

    fun returnBook(returnDate: String = todayString()): Boolean {
        if (!isActive() || !isValidDate(returnDate)) return false
        actualReturnDate = returnDate
        book.returnBook()
        return true
    }

    fun getStatus(): String {
        val returnedDate = actualReturnDate
        if (returnedDate != null) {
            val lateDays = daysBetween(dueDate, returnedDate)
            return if (lateDays > 0) "Đã trả - Trễ $lateDays ngày" else "Đã trả"
        }

        val today = todayString()
        return if (daysBetween(dueDate, today) > 0) "Quá hạn" else "Đang mượn"
    }

    fun getLateDays(): Long {
        val endDate = actualReturnDate ?: todayString()
        return maxOf(0L, daysBetween(dueDate, endDate))
    }

    fun getDescription(): String = "${student.getPersonName()} - ${book.getTitle()}"

    companion object {
        private const val DATE_PATTERN = "dd/MM/yyyy"

        private fun newDateFormat(): SimpleDateFormat {
            return SimpleDateFormat(DATE_PATTERN, Locale.US).apply {
                isLenient = false
                // So sánh theo ngày lịch, tránh lệch số ngày do giờ mùa hè.
                timeZone = TimeZone.getTimeZone("UTC")
            }
        }
        fun todayString(): String {
            val localFormat = SimpleDateFormat(DATE_PATTERN, Locale.US)
            return localFormat.format(Date())
        }

        fun parseDate(value: String): Date? {
            val format = newDateFormat()
            val position = ParsePosition(0)
            val parsed = format.parse(value, position)
            return if (parsed != null && position.index == value.length) parsed else null
        }

        fun isValidDate(value: String): Boolean = parseDate(value) != null


        fun daysBetween(start: String, end: String): Long {
            val startDate = parseDate(start) ?: return 0L
            val endDate = parseDate(end) ?: return 0L
            val millisPerDay = 24L * 60L * 60L * 1000L
            return (endDate.time - startDate.time) / millisPerDay
        }
    }
}