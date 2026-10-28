
package com.example.navigationandoop_buoi4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.navigationandoop_buoi4.ui.theme.TodoBlue
import com.example.navigationandoop_buoi4.ui.theme.TodoIndicatorInactive

@Composable
fun OnboardingIndicator(
    currentPage: Int,
    pageCount: Int = 3,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage

            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .width(if (isSelected) 24.dp else 8.dp)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) TodoBlue
                        else TodoIndicatorInactive
                    )
            )
        }
    }
}
