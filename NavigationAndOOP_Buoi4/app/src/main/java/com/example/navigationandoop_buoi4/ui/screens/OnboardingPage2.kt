
package com.example.navigationandoop_buoi4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationandoop_buoi4.ui.components.IllustrationCard
import com.example.navigationandoop_buoi4.ui.components.IllustrationType
import com.example.navigationandoop_buoi4.ui.theme.TodoTextPrimary
import com.example.navigationandoop_buoi4.ui.theme.TodoTextSecondary
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height

@Composable
fun OnboardingPage2(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            IllustrationCard(
                type = IllustrationType.CATEGORIES,
                modifier = Modifier
                    .width(280.dp)
                    .height(220.dp)
            )
        }

        Text(
            text = "Chia theo Học tập và Cuộc sống",
            modifier = Modifier.padding(top = 12.dp),
            fontSize = 23.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            color = TodoTextPrimary
        )

        Text(
            text = "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = TodoTextSecondary
        )
    }
}
