
package com.example.navigationandoop_buoi4.data

class OnboardingRepository {

    fun getItems(): List<OnboardingItem> {
        return listOf(
            OnboardingItem(
                title = "Mọi việc, gọn trong một\nchỗ",
                description =
                    "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào.",
                illustration = OnboardingIllustration.TASKS,
                buttonText = "Tiếp tục"
            ),
            OnboardingItem(
                title = "Chia theo Học tập và Cuộc sống",
                description =
                    "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần lo gì.",
                illustration = OnboardingIllustration.CATEGORIES,
                buttonText = "Tiếp tục"
            ),
            OnboardingItem(
                title = "Nhắc đúng lúc",
                description =
                    "Bật thông báo để UTH Todo nhắc bạn trước hạn chót.",
                illustration = OnboardingIllustration.REMINDERS,
                buttonText = "Bắt đầu ngay"
            )
        )
    }
}
