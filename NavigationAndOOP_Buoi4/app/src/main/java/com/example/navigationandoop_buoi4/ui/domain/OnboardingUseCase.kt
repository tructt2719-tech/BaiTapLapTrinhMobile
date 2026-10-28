
package com.example.navigationandoop_buoi4.domain

import com.example.navigationandoop_buoi4.data.OnboardingItem
import com.example.navigationandoop_buoi4.data.OnboardingRepository

class OnboardingUseCase(
    private val repository: OnboardingRepository
) {
    // Lấy dữ liệu một lần và tái sử dụng
    private val pages: List<OnboardingItem> = repository.getItems()

    fun getPages(): List<OnboardingItem> {
        return pages
    }

    fun getNextPage(currentPage: Int): Int {
        if (pages.isEmpty()) return 0

        val lastPage = pages.lastIndex
        return (currentPage + 1).coerceAtMost(lastPage)
    }

    fun isLastPage(currentPage: Int): Boolean {
        return pages.isNotEmpty() &&
                currentPage >= pages.lastIndex
    }
}
