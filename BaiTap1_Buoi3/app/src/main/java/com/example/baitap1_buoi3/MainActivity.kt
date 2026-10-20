
package com.example.baitap1_buoi3
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Path
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF2196F3)
private val LightBlue = Color(0xFFBBDEFB)
private val BackgroundColor = Color(0xFFF7F9FC)
private val TextColor = Color(0xFF222222)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                    BaiTap1Buoi3App()
                }
            }
        }
    }
}

private enum class Screen {
    Welcome, Components, TextDetail, ImageDetail, TextFieldDetail, PasswordDetail,
    ColumnDetail, RowDetail
}

@Composable
fun BaiTap1Buoi3App() {
    var currentScreen by remember { mutableStateOf(Screen.Welcome) }

    when (currentScreen) {
        Screen.Welcome -> WelcomeScreen(
            onReady = { currentScreen = Screen.Components }
        )

        Screen.Components -> ComponentsScreen(
            onBack = { currentScreen = Screen.Welcome },
            onOpen = { currentScreen = it }
        )

        Screen.TextDetail -> TextDetailScreen(
            onBack = { currentScreen = Screen.Components }
        )

        Screen.ImageDetail -> ComponentDetailScreen(
            title = "Image",
            description = "Displays an image",
            onBack = { currentScreen = Screen.Components }
        )

        Screen.TextFieldDetail -> TextFieldDetailScreen(
            onBack = { currentScreen = Screen.Components }
        )

        Screen.PasswordDetail -> PasswordDetailScreen(
            onBack = { currentScreen = Screen.Components }
        )

        Screen.ColumnDetail -> ComponentDetailScreen(
            title = "Column",
            description = "Arranges elements vertically",
            onBack = { currentScreen = Screen.Components }
        )

        Screen.RowDetail -> ComponentDetailScreen(
            title = "Row",
            description = "Arranges elements horizontally",
            onBack = { currentScreen = Screen.Components }
        )
    }
}


@Composable
private fun WelcomeScreen(onReady: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Dòng chữ phía trên
        Text(
            text = "I'm Ready",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Đẩy cụm logo lên cao hơn
        Spacer(modifier = Modifier.weight(0.8f))

        ComposeLogo()

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Jetpack Compose",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 13.sp,
            lineHeight = 19.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1.4f))

        // Nút phía dưới
        Button(
            onClick = onReady,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "I'm ready",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
private fun ComposeLogo() {
    Canvas(
        modifier = Modifier.size(150.dp)
    ) {
        val sx = size.width / 150f
        val sy = size.height / 150f

        fun polygon(
            points: List<Pair<Float, Float>>,
            color: Color
        ) {
            val path = Path().apply {
                moveTo(points[0].first * sx, points[0].second * sy)

                for (i in 1 until points.size) {
                    lineTo(
                        points[i].first * sx,
                        points[i].second * sy
                    )
                }

                close()
            }

            drawPath(path, color)
        }

        // Mặt bên trái: xanh đen
        polygon(
            listOf(
                20f to 38f,
                75f to 70f,
                75f to 145f,
                20f to 112f
            ),
            Color(0xFF073B4C)
        )

        // Mặt bên phải: xanh dương
        polygon(
            listOf(
                75f to 70f,
                130f to 38f,
                130f to 112f,
                75f to 145f
            ),
            Color(0xFF4285F4)
        )

        // Mặt trên: xanh lá
        polygon(
            listOf(
                20f to 38f,
                75f to 6f,
                130f to 38f,
                75f to 70f
            ),
            Color(0xFF21C982)
        )

        // Phần rỗng ở giữa logo
        polygon(
            listOf(
                75f to 42f,
                97f to 55f,
                97f to 81f,
                75f to 94f,
                53f to 81f,
                53f to 55f
            ),
            Color(0xFFDDF4FF)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComponentsScreen(
    onBack: () -> Unit,
    onOpen: (Screen) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "UI Components List",
                        color = Color(0xFF2997D8),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Text(
                        text = "‹",
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(horizontal = 16.dp),
                        fontSize = 32.sp,
                        color = Blue
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryTitle("Display")

            ComponentCard(
                title = "Text",
                description = "Displays text",
                onClick = { onOpen(Screen.TextDetail) }
            )

            ComponentCard(
                title = "Image",
                description = "Displays an image",
                onClick = { onOpen(Screen.ImageDetail) }
            )

            Spacer(modifier = Modifier.height(8.dp))
            CategoryTitle("Input")

            ComponentCard(
                title = "TextField",
                description = "Input field for text",
                onClick = { onOpen(Screen.TextFieldDetail) }
            )

            ComponentCard(
                title = "PasswordField",
                description = "Input field for passwords",
                onClick = { onOpen(Screen.PasswordDetail) }
            )

            Spacer(modifier = Modifier.height(8.dp))
            CategoryTitle("Layout")

            ComponentCard(
                title = "Column",
                description = "Arranges elements vertically",
                onClick = { onOpen(Screen.ColumnDetail) }
            )

            ComponentCard(
                title = "Row",
                description = "Arranges elements horizontally",
                onClick = { onOpen(Screen.RowDetail) }
            )
        }
    }
}

@Composable
private fun CategoryTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        color = TextColor,
        fontWeight = FontWeight.Medium
    )
}

@Composable
private fun ComponentCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                LightBlue,
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = description,
            fontSize = 13.sp,
            color = TextColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScaffold(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color(0xFF2997D8),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Text(
                        text = "‹",
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(horizontal = 16.dp),
                        fontSize = 32.sp,
                        color = Blue
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
private fun TextDetailScreen(onBack: () -> Unit) {
    DetailScaffold(
        title = "Text Detail",
        onBack = onBack
    ) {
        Text(
            text = buildAnnotatedString {
                append("The ")
                pushStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.LineThrough
                    )
                )
                append("quick")
                pop()
                append(" ")

                pushStyle(
                    SpanStyle(color = Color(0xFFB47A2C))
                )
                append("Brown")
                pop()

                append("\nfox ")

                pushStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                )
                append("jumps")
                pop()

                append(" ")

                pushStyle(
                    SpanStyle(fontWeight = FontWeight.Bold)
                )
                append("over")
                pop()

                append("\n")

                pushStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
                append("the lazy")
                pop()

                append(" dog.")
            },
            fontSize = 28.sp,
            lineHeight = 44.sp,
            color = TextColor,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFEEEEEE))
        )
    }
}

@Composable
private fun ComponentDetailScreen(
    title: String,
    description: String,
    onBack: () -> Unit
) {
    DetailScaffold(title = title, onBack = onBack) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Blue
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = TextColor
        )
    }
}

@Composable
private fun TextFieldDetailScreen(onBack: () -> Unit) {
    var text by remember { mutableStateOf("") }

    DetailScaffold(title = "TextField Detail", onBack = onBack) {
        Text(
            text = "TextField",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "You entered: $text")
    }
}

@Composable
private fun PasswordDetailScreen(onBack: () -> Unit) {
    var password by remember { mutableStateOf("") }

    DetailScaffold(title = "PasswordField Detail", onBack = onBack) {
        Text(
            text = "PasswordField",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Password length: ${password.length}")
    }
}
