
package com.example.navigationandoop_buoi4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LogoBlue = Color(0xFF3659D9)
private val LogoText = Color(0xFF252525)
private val TodoBlue = Color(0xFF647AAE)
private val LightBlue = Color(0xFF9DB2EB)

@Composable
fun TodoLogo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nền xanh bên ngoài
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(LogoBlue)
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                // Dòng trên: dấu tích và thanh dài màu xanh đậm
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✓",
                            color = LogoBlue,
                            fontSize = 7.sp,
                            lineHeight = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height(2.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color.White)
                    )
                }

                // Dòng dưới: vòng tròn và thanh ngắn màu xanh nhạt
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .clip(CircleShape)
                            .background(LightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(LogoBlue)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(7.dp)
                            .height(2.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(LightBlue)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Tên ứng dụng
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = LogoText,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("UTH ")
                }

                withStyle(
                    SpanStyle(
                        color = TodoBlue,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append("Todo")
                }
            },
            fontSize = 18.sp,
            maxLines = 1
        )
    }
}
