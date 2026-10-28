
package com.example.navigationandoop_buoi4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationandoop_buoi4.data.OnboardingIllustration
import com.example.navigationandoop_buoi4.domain.OnboardingUseCase
import com.example.navigationandoop_buoi4.ui.components.IllustrationCard
import com.example.navigationandoop_buoi4.ui.components.IllustrationType
import com.example.navigationandoop_buoi4.ui.components.OnboardingIndicator
import com.example.navigationandoop_buoi4.ui.components.PrimaryButton
import com.example.navigationandoop_buoi4.ui.components.TodoLogo
import com.example.navigationandoop_buoi4.ui.theme.TodoTextPrimary
import com.example.navigationandoop_buoi4.ui.theme.TodoTextSecondary

@Composable
fun OnboardingScreen(
    useCase: OnboardingUseCase,
    onFinished: () -> Unit
) {
    val pages = remember { useCase.getPages() }
    var currentPage by remember { mutableIntStateOf(0) }
    val page = pages[currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // 1. Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TodoLogo()

            TextButton(onClick = onFinished) {
                Text(
                    text = "Bỏ qua",
                    color = Color(0xFF777777),
                    fontSize = 12.sp
                )
            }
        }

        // 2. Nội dung chính
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Đẩy hình xuống dưới header
            Spacer(modifier = Modifier.height(40.dp))

            val illustrationType = when (page.illustration) {
                OnboardingIllustration.TASKS ->
                    IllustrationType.TASKS

                OnboardingIllustration.CATEGORIES ->
                    IllustrationType.CATEGORIES

                OnboardingIllustration.REMINDERS ->
                    IllustrationType.REMINDERS
            }

            // Hình minh họa nằm giữa theo chiều ngang
            BoxForIllustration(
                type = illustrationType
            )

            // Khoảng trống linh hoạt để đẩy chữ xuống thấp hơn
            Spacer(modifier = Modifier.height(140.dp))

            // Tiêu đề
            Text(
                text = page.title,
                modifier = Modifier.width(280.dp),
                fontSize = 23.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.Bold,
                color = TodoTextPrimary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Mô tả
            Text(
                text = page.description,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = TodoTextSecondary
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        // 3. Footer nằm gần đáy màn hình
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OnboardingIndicator(
                currentPage = currentPage,
                pageCount = pages.size
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = page.buttonText,
                onClick = {
                    if (useCase.isLastPage(currentPage)) {
                        onFinished()
                    } else {
                        currentPage = useCase.getNextPage(currentPage)
                    }
                }
            )

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun BoxForIllustration(
    type: IllustrationType
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IllustrationCard(
            type = type,
            modifier = Modifier
                .width(194.dp)
                .height(230.dp)
        )
    }
}
