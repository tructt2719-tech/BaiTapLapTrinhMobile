package com.example.hethongquanlythuvien_buoi4.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.hethongquanlythuvien_buoi4.model.Book
import com.example.hethongquanlythuvien_buoi4.model.Librarian
import com.example.hethongquanlythuvien_buoi4.model.Loan
import com.example.hethongquanlythuvien_buoi4.model.Person
import com.example.hethongquanlythuvien_buoi4.model.Student

class LibraryRepository {
    private val students = mutableStateListOf(
        Student("SV01", "Nguyen Van A", "221100000001"),
        Student("SV02", "Tran Thi B", "221100000002"),
        Student("SV03", "Le Van C", "221100000003")
    )

    private val books = mutableStateListOf(
        Book("B01", "Sách 01", "Tác giả A", "01/10/2026"),
        Book("B02", "Sách 02", "Tác giả B", "05/10/2026")
    )

    private val loans = mutableStateListOf<Loan>()

    fun getStudents(): SnapshotStateList<Student> = students
    fun getBooks(): SnapshotStateList<Book> = books
    fun getLoans(): SnapshotStateList<Loan> = loans
    fun getLibrarian(): Person = Librarian("NV01", "Thu Thu", "NV001")

    // Cập nhật hàm để nhận thêm mã số sinh viên tự nhập
    fun findOrCreateStudent(name: String, studentId: String): Student {
        val trimmedName = name.trim()
        val trimmedId = studentId.trim()

        val existing = students.find {
            it.getPersonName().equals(trimmedName, ignoreCase = true) ||
                    it.getPersonId() == trimmedId
        }
        if (existing != null) return existing

        val newStudent = Student("SV0${students.size + 1}", trimmedName, trimmedId)
        students.add(newStudent)
        return newStudent
    }

    fun addBook(id: String, title: String, author: String, entryDate: String): Boolean {
        if (id.isBlank() || title.isBlank() || author.isBlank()) return false
        if (!Loan.isValidDate(entryDate)) return false
        if (books.any { it.getId().equals(id.trim(), ignoreCase = true) }) return false
        books.add(Book(id.trim(), title.trim(), author.trim(), entryDate.trim()))
        return true
    }

    fun isBookAvailable(book: Book): Boolean =
        loans.none { it.getBook().getId() == book.getId() && it.isActive() } &&
                !book.isBorrowed()

    fun borrowBook(student: Student, book: Book, borrowDate: String, dueDate: String): Boolean {
        if (!isBookAvailable(book)) return false
        if (!Loan.isValidDate(borrowDate) || !Loan.isValidDate(dueDate)) return false
        if (Loan.daysBetween(borrowDate, dueDate) < 0) return false
        val loan = Loan(student, book, borrowDate.trim(), dueDate.trim())
        if (!loan.borrowBook()) return false
        loans.add(loan)
        return true
    }

    fun returnBook(loan: Loan, actualReturnDate: String = Loan.todayString()): Boolean {
        if (!Loan.isValidDate(actualReturnDate)) return false
        return loan.returnBook(actualReturnDate)
    }
}