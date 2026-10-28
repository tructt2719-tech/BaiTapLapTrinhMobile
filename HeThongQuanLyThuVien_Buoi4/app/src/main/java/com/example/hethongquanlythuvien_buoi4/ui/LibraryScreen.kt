package com.example.hethongquanlythuvien_buoi4.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hethongquanlythuvien_buoi4.data.LibraryRepository
import com.example.hethongquanlythuvien_buoi4.model.Book
import com.example.hethongquanlythuvien_buoi4.model.Loan
import com.example.hethongquanlythuvien_buoi4.model.Student

private val PrimaryBlue = Color(0xFF2C55A2)
private val CheckRed = Color(0xFFA3334D)
private val PanelGray = Color(0xFFE0E0E0)
private val DarkText = Color(0xFF000000)
private val BorderGray = Color(0xFF8E8E93)
private val UnselectedGray = Color(0xFFB0B0B0)

@Composable
fun LibraryScreen() {
    val repository = remember { LibraryRepository() }
    val students = repository.getStudents()
    val books = repository.getBooks()
    val loans = repository.getLoans()

    val checkedIds = remember { mutableStateListOf("B01", "B02") }

    var studentIndex by remember { mutableIntStateOf(0) }
    var selectedTab by remember { mutableIntStateOf(0) }
    var showStudentDialog by remember { mutableStateOf(false) }

    // State cho Dialog Thêm Sách Mới (Tab Quản lý)
    var showAddBookDialog by remember { mutableStateOf(false) }
    var newBookId by remember { mutableStateOf("") }
    var newBookTitle by remember { mutableStateOf("") }
    var newBookAuthor by remember { mutableStateOf("") }
    var newBookEntryDate by remember { mutableStateOf("") }
    var addBookError by remember { mutableStateOf("") }

    // State cho Dialog Tạo Phiếu Mượn (Tab DS Sách)
    var showLoanDialog by remember { mutableStateOf(false) }
    var customBorrowerName by remember { mutableStateOf("") }
    var customStudentId by remember { mutableStateOf("") }
    var selectedBookForLoan by remember { mutableStateOf<Book?>(null) }
    var borrowDateInput by remember { mutableStateOf(Loan.todayString()) }
    var dueDateInput by remember { mutableStateOf("") }
    var loanError by remember { mutableStateOf("") }

    // State cho Dialog Xem Chi Tiết & Trả Sách (Tab Sinh viên)
    var selectedStudentForDetail by remember { mutableStateOf<Student?>(null) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFCCCCCC))
            NavigationBar(
                modifier = Modifier.height(60.dp),
                containerColor = Color.White,
                tonalElevation = 0.dp,
                windowInsets = WindowInsets(0.dp)
            ) {
                val labels = listOf("Quản lý", "DS Sách", "Sinh viên")
                val icons = listOf(Icons.Default.Home, Icons.Default.List, Icons.Default.Person)

                labels.forEachIndexed { index, label ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = label,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = UnselectedGray,
                            unselectedTextColor = UnselectedGray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when (selectedTab) {
                    // TAB 0: QUẢN LÝ
                    0 -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Sinh viên",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkText
                                )
                                Spacer(Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(42.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        color = Color.White,
                                        border = BorderStroke(1.dp, BorderGray)
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = 12.dp),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            Text(
                                                text = students.getOrNull(studentIndex)?.getPersonName() ?: "",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = DarkText,
                                                maxLines = 1
                                            )
                                        }
                                    }

                                    Button(
                                        onClick = { showStudentDialog = true },
                                        modifier = Modifier
                                            .width(95.dp)
                                            .height(42.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        contentPadding = PaddingValues(0.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                                    ) {
                                        Text("Thay đổi", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }

                            Column {
                                Text(
                                    text = "Danh sách sách",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkText
                                )
                                Spacer(Modifier.height(8.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(170.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(PanelGray)
                                        .padding(horizontal = 12.dp, vertical = 12.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    books.take(2).forEach { book ->
                                        CompactBookRow(
                                            book = book,
                                            checked = checkedIds.contains(book.getId()),
                                            onCheckedChange = { checked ->
                                                if (checked) {
                                                    if (!checkedIds.contains(book.getId())) checkedIds.add(book.getId())
                                                } else {
                                                    checkedIds.remove(book.getId())
                                                }
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    newBookId = "B0${books.size + 1}"
                                    newBookTitle = ""
                                    newBookAuthor = ""
                                    newBookEntryDate = Loan.todayString()
                                    addBookError = ""
                                    showAddBookDialog = true
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .width(130.dp)
                                    .height(42.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                            ) {
                                Text("Thêm", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // TAB 1: DS SÁCH
                    1 -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Danh sách sách",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkText
                            )

                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(books) { book ->
                                    val activeLoan = loans.find { it.getBook().getId() == book.getId() && it.isActive() }

                                    Surface(
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        color = PanelGray
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = "[${book.getId()}] ${book.getTitle()}",
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = DarkText
                                                )
                                                Text(
                                                    text = "Ngày nhập: ${book.getEntryDate()}",
                                                    fontSize = 12.sp,
                                                    color = Color(0xFF555555)
                                                )
                                            }
                                            Text(
                                                text = "Tác giả: ${book.getAuthor()}",
                                                fontSize = 13.sp,
                                                color = Color(0xFF444444)
                                            )

                                            Spacer(Modifier.height(4.dp))

                                            if (activeLoan != null) {
                                                Text(
                                                    text = "Trạng thái: Đang mượn bởi ${activeLoan.getStudent().getPersonName()}",
                                                    fontSize = 12.sp,
                                                    color = CheckRed,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            } else {
                                                Text(
                                                    text = "Trạng thái: Có sẵn",
                                                    fontSize = 12.sp,
                                                    color = PrimaryBlue,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            Button(
                                onClick = {
                                    val currentStudent = students.getOrNull(studentIndex)
                                    customBorrowerName = currentStudent?.getPersonName() ?: ""
                                    // Đảm bảo MSSV mặc định luôn đạt 12 số
                                    customStudentId = currentStudent?.getPersonId()?.padStart(12, '0') ?: ""
                                    selectedBookForLoan = books.firstOrNull { repository.isBookAvailable(it) }
                                    borrowDateInput = Loan.todayString()
                                    dueDateInput = ""
                                    loanError = ""
                                    showLoanDialog = true
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .width(130.dp)
                                    .height(42.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                            ) {
                                Text("Thêm", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.height(8.dp))
                        }
                    }

                    // TAB 2: SINH VIÊN
                    2 -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Spacer(Modifier.height(16.dp))
                            Text(
                                text = "Danh sách sinh viên",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkText
                            )

                            Spacer(Modifier.height(12.dp))

                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                items(students) { student ->
                                    val studentLoans = loans.filter {
                                        it.getStudent().getPersonId() == student.getPersonId() ||
                                                it.getStudent().getPersonName().equals(student.getPersonName(), ignoreCase = true)
                                    }

                                    Surface(
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color.White,
                                        border = BorderStroke(1.dp, Color(0xFFE5E5EA)),
                                        shadowElevation = 1.dp
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 14.dp, vertical = 10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = "Sinh viên: ${student.getPersonName()}",
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = DarkText
                                                )
                                                Text(
                                                    text = "MSSV: ${student.getPersonId()}",
                                                    fontSize = 13.sp,
                                                    color = Color(0xFF666666)
                                                )

                                                Spacer(Modifier.height(4.dp))

                                                if (studentLoans.isEmpty()) {
                                                    Text("Chưa mượn sách nào", fontSize = 12.sp, color = Color.Gray)
                                                } else {
                                                    studentLoans.forEach { loan ->
                                                        val statusText = loan.getStatus()
                                                        val statusColor = when {
                                                            statusText.startsWith("Đã trả") -> Color(0xFF2E7D32)
                                                            statusText == "Quá hạn" -> CheckRed
                                                            else -> PrimaryBlue
                                                        }
                                                        Text(
                                                            text = "• [${loan.getBook().getId()}] ${loan.getBook().getTitle()} (${loan.getBook().getAuthor()}) - $statusText",
                                                            fontSize = 12.sp,
                                                            color = statusColor,
                                                            fontWeight = FontWeight.Medium
                                                        )
                                                    }
                                                }
                                            }

                                            Button(
                                                onClick = { selectedStudentForDetail = student },
                                                modifier = Modifier.height(34.dp),
                                                shape = RoundedCornerShape(6.dp),
                                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                                            ) {
                                                Text("Chọn", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showStudentDialog) {
        AlertDialog(
            onDismissRequest = { showStudentDialog = false },
            title = { Text("Chọn sinh viên") },
            text = {
                Column {
                    students.forEachIndexed { index, student ->
                        TextButton(
                            onClick = {
                                studentIndex = index
                                showStudentDialog = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = student.getPersonName(),
                                color = DarkText,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showStudentDialog = false }) { Text("Đóng") }
            }
        )
    }

    if (showAddBookDialog) {
        AlertDialog(
            onDismissRequest = { showAddBookDialog = false },
            title = { Text("Thêm sách vào kho") },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = newBookId,
                        onValueChange = { newBookId = it },
                        label = { Text("Mã số sách") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newBookTitle,
                        onValueChange = { newBookTitle = it },
                        label = { Text("Tên sách") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newBookAuthor,
                        onValueChange = { newBookAuthor = it },
                        label = { Text("Tên tác giả") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newBookEntryDate,
                        onValueChange = { newBookEntryDate = it },
                        label = { Text("Ngày nhập kho (dd/MM/yyyy)") },
                        singleLine = true
                    )

                    if (addBookError.isNotBlank()) {
                        Text(addBookError, color = CheckRed, fontSize = 12.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val success = repository.addBook(
                            id = newBookId,
                            title = newBookTitle,
                            author = newBookAuthor,
                            entryDate = newBookEntryDate
                        )
                        if (success) {
                            showAddBookDialog = false
                        } else {
                            addBookError = "Thông tin không hợp lệ hoặc Mã sách đã tồn tại!"
                        }
                    }
                ) { Text("Lưu sách", color = PrimaryBlue, fontWeight = FontWeight.Bold) }
            },
            dismissButton = {
                TextButton(onClick = { showAddBookDialog = false }) { Text("Hủy") }
            }
        )
    }

    // DIALOG TẠO PHIẾU MƯỢN SÁCH: Cho phép tự do nhập tay MSSV (Bắt buộc 12 số)
    if (showLoanDialog) {
        AlertDialog(
            onDismissRequest = { showLoanDialog = false },
            title = { Text("Tạo phiếu mượn sách") },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Thông tin người mượn:", fontWeight = FontWeight.Bold)

                    OutlinedTextField(
                        value = customBorrowerName,
                        onValueChange = { customBorrowerName = it },
                        label = { Text("Tên người mượn") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = customStudentId,
                        onValueChange = { input ->
                            // Cho phép nhập tay trực tiếp, chỉ lọc chữ số và khống chế tối đa 12 ký tự
                            val digitsOnly = input.filter { it.isDigit() }
                            if (digitsOnly.length <= 12) {
                                customStudentId = digitsOnly
                            }
                        },
                        label = { Text("Mã số sinh viên (Đúng 12 chữ số)") },
                        placeholder = { Text("VD: 221100000001") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Hoặc chọn nhanh Sinh viên:", fontSize = 12.sp, color = Color.Gray)
                    Column {
                        students.forEach { student ->
                            val formattedId = student.getPersonId().padStart(12, '0')
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        customBorrowerName = student.getPersonName()
                                        customStudentId = formattedId
                                    }
                                    .padding(vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = customBorrowerName == student.getPersonName() && customStudentId == formattedId,
                                    onClick = {
                                        customBorrowerName = student.getPersonName()
                                        customStudentId = formattedId
                                    }
                                )
                                Text("${student.getPersonName()} ($formattedId)")
                            }
                        }
                    }

                    Text("Chọn sách mượn (Sách có sẵn):", fontWeight = FontWeight.Bold)
                    val availableBooks = books.filter { repository.isBookAvailable(it) }

                    if (availableBooks.isEmpty()) {
                        Text("Hiện không có sách nào có sẵn trong kho!", color = CheckRed, fontSize = 12.sp)
                    } else {
                        availableBooks.forEach { book ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedBookForLoan = book }
                                    .padding(vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedBookForLoan?.getId() == book.getId(),
                                    onClick = { selectedBookForLoan = book }
                                )
                                Text("[${book.getId()}] ${book.getTitle()} - ${book.getAuthor()}")
                            }
                        }
                    }

                    OutlinedTextField(
                        value = borrowDateInput,
                        onValueChange = { borrowDateInput = it },
                        label = { Text("Ngày mượn (dd/MM/yyyy)") },
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = dueDateInput,
                        onValueChange = { dueDateInput = it },
                        label = { Text("Ngày trả dự kiến (dd/MM/yyyy)") },
                        placeholder = { Text("dd/MM/yyyy") },
                        singleLine = true
                    )

                    if (loanError.isNotBlank()) {
                        Text(loanError, color = CheckRed, fontSize = 12.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val book = selectedBookForLoan
                        val borrowerName = customBorrowerName.trim()
                        val borrowerId = customStudentId.trim()

                        if (borrowerName.isBlank()) {
                            loanError = "Tên người mượn không được bỏ trống!"
                            return@TextButton
                        }

                        // KIỂM TRA BẮT BUỘC ĐỦ ĐÚNG 12 CHỮ SỐ
                        if (borrowerId.length != 12) {
                            loanError = "Mã số sinh viên phải chứa đủ đúng 12 chữ số!"
                            return@TextButton
                        }

                        if (book == null) {
                            loanError = "Vui lòng chọn sách muốn mượn!"
                            return@TextButton
                        }

                        val studentToUse = repository.findOrCreateStudent(borrowerName, borrowerId)

                        val success = repository.borrowBook(
                            student = studentToUse,
                            book = book,
                            borrowDate = borrowDateInput,
                            dueDate = dueDateInput
                        )

                        if (success) {
                            showLoanDialog = false
                        } else {
                            loanError = "Không thể tạo phiếu mượn! Kiểm tra lại định dạng ngày."
                        }
                    }
                ) { Text("Lưu phiếu mượn", color = PrimaryBlue, fontWeight = FontWeight.Bold) }
            },
            dismissButton = {
                TextButton(onClick = { showLoanDialog = false }) { Text("Hủy") }
            }
        )
    }

    selectedStudentForDetail?.let { student ->
        val studentLoans = loans.filter {
            it.getStudent().getPersonId() == student.getPersonId() ||
                    it.getStudent().getPersonName().equals(student.getPersonName(), ignoreCase = true)
        }

        AlertDialog(
            onDismissRequest = { selectedStudentForDetail = null },
            title = { Text("Chi tiết mượn sách: ${student.getPersonName()}") },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (studentLoans.isEmpty()) {
                        Text("Sinh viên này hiện không mượn cuốn sách nào.")
                    } else {
                        studentLoans.forEach { loan ->
                            val statusText = loan.getStatus()
                            val statusColor = when {
                                statusText.startsWith("Đã trả") -> Color(0xFF2E7D32)
                                statusText == "Quá hạn" -> CheckRed
                                else -> PrimaryBlue
                            }

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F7))
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text("Mã sách: ${loan.getBook().getId()}", fontWeight = FontWeight.Bold)
                                    Text("Tên sách: ${loan.getBook().getTitle()}")
                                    Text("Tác giả: ${loan.getBook().getAuthor()}")
                                    Text("Ngày mượn: ${loan.getBorrowDate()}")
                                    Text("Ngày trả dự kiến: ${loan.getDueDate()}")
                                    if (loan.getActualReturnDate() != null) {
                                        Text("Ngày trả thực tế: ${loan.getActualReturnDate()}")
                                    }
                                    Text("Trạng thái: $statusText", color = statusColor, fontWeight = FontWeight.Bold)

                                    Spacer(Modifier.height(8.dp))

                                    if (loan.isActive()) {
                                        Button(
                                            onClick = {
                                                repository.returnBook(loan, Loan.todayString())
                                                selectedStudentForDetail = null
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                                        ) {
                                            Text("Xác nhận Trả sách")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedStudentForDetail = null }) {
                    Text("Đóng", color = PrimaryBlue, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

@Composable
private fun CompactBookRow(
    book: Book,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp))
            .clickable { onCheckedChange(!checked) },
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (checked) CheckRed else Color.White)
                    .then(
                        if (!checked) Modifier.border(1.dp, BorderGray, RoundedCornerShape(4.dp))
                        else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (checked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = book.getTitle(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF333333)
            )
        }
    }
}