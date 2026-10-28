
package com.example.navigationandoop_buoi4.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF3D5AE8)
private val PalePurple = Color(0xFFEEF0FC)
private val GrayLine = Color(0xFFE0E1E6)
private val BellBlue = Color(0xFF5267A8)

enum class IllustrationType {
    TASKS,
    CATEGORIES,
    REMINDERS
}

@Composable
fun IllustrationCard(
    type: IllustrationType,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(PalePurple)
            .padding(horizontal = 10.dp, vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        when (type) {
            IllustrationType.TASKS -> TasksIllustration()
            IllustrationType.CATEGORIES -> CategoriesIllustration()
            IllustrationType.REMINDERS -> RemindersIllustration()
        }
    }
}

// Trang 1: danh sách công việc
@Composable
private fun TasksIllustration() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(3) { index ->
            TaskPreviewRow(checked = index == 0)
        }
    }
}

@Composable
private fun TaskPreviewRow(checked: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White)
            .border(
                0.5.dp,
                Color(0xFFF0F0F0),
                RoundedCornerShape(13.dp)
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(if (checked) Blue else Color.White)
                .then(
                    if (checked) Modifier
                    else Modifier.border(
                        1.5.dp,
                        Color(0xFFBDBDBD),
                        CircleShape
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .height(6.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(GrayLine)
        )
    }
}

// Trang 2: nhóm công việc
@Composable
private fun CategoriesIllustration() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CategoryPreview(
            label = "Học tập",
            labelBackground = Color(0xFFE5ECFA),
            labelColor = Color(0xFF6379A7),
            lineWidth = 70.dp
        )

        CategoryPreview(
            label = "Cuộc sống",
            labelBackground = Color(0xFFFFF0D9),
            labelColor = Color(0xFFAA8955),
            lineWidth = 48.dp
        )
    }
}

@Composable
private fun CategoryPreview(
    label: String,
    labelBackground: Color,
    labelColor: Color,
    lineWidth: androidx.compose.ui.unit.Dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(
                0.5.dp,
                Color(0xFFF1F1F1),
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(labelBackground)
                .padding(horizontal = 7.dp, vertical = 3.dp),
            color = labelColor,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.Medium
        )

        Box(
            modifier = Modifier
                .width(lineWidth)
                .height(5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(GrayLine)
        )
    }
}

// Trang 3: nhắc nhở
@Composable
private fun RemindersIllustration() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Vòng tròn trắng chứa chuông nét xanh
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            BellOutline(
                modifier = Modifier.size(32.dp),
                color = BellBlue
            )
        }

        // Thanh thông báo nhỏ bên dưới
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Ôn bài Toán lúc 19:00",
                fontSize = 9.sp,
                lineHeight = 12.sp,
                color = Color(0xFF424242),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun BellOutline(
    modifier: Modifier = Modifier,
    color: Color = BellBlue
) {
    Canvas(modifier = modifier) {
        val sx = size.width / 32f
        val sy = size.height / 32f

        fun point(x: Float, y: Float) =
            Offset(x * sx, y * sy)

        val bellPath = Path().apply {
            moveTo(8f * sx, 22f * sy)
            lineTo(10f * sx, 19f * sy)
            lineTo(10f * sx, 13f * sy)
            cubicTo(
                10f * sx, 6f * sy,
                13f * sx, 4f * sy,
                16f * sx, 4f * sy
            )
            cubicTo(
                19f * sx, 4f * sy,
                22f * sx, 6f * sy,
                22f * sx, 13f * sy
            )
            lineTo(22f * sx, 19f * sy)
            lineTo(24f * sx, 22f * sy)
            close()
        }

        drawPath(
            path = bellPath,
            color = color,
            style = Stroke(width = 1.8.dp.toPx())
        )

        drawLine(
            color = color,
            start = point(8f, 22f),
            end = point(24f, 22f),
            strokeWidth = 1.8.dp.toPx()
        )

        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(13f * sx, 22f * sy),
            size = Size(6f * sx, 5f * sy),
            style = Stroke(width = 1.8.dp.toPx())
        )

        drawCircle(
            color = color,
            radius = 1.2.dp.toPx(),
            center = point(16f, 2.5f)
        )
    }
}
