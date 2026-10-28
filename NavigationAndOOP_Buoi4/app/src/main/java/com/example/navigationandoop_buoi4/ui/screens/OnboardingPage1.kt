
package com.example.navigationandoop_buoi4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationandoop_buoi4.ui.components.IllustrationCard
import com.example.navigationandoop_buoi4.ui.components.IllustrationType
import com.example.navigationandoop_buoi4.ui.theme.TodoTextPrimary
import com.example.navigationandoop_buoi4.ui.theme.TodoTextSecondary

@Composable
fun OnboardingPage1(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Khung minh họa nằm giữa màn hình
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IllustrationCard(
                type = IllustrationType.TASKS,
                modifier = Modifier
                    .width(180.dp)
                    .height(190.dp)
            )
        }

        // Khoảng cách từ hình minh họa đến tiêu đề
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Mọi việc, gọn trong một chỗ",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            fontSize = 23.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            color = TodoTextPrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = TodoTextSecondary
        )
    }
}
