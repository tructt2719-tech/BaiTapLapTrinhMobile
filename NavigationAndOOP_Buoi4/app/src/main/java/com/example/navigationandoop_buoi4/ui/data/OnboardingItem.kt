
package com.example.navigationandoop_buoi4.data

enum class OnboardingIllustration {
    TASKS,
    CATEGORIES,
    REMINDERS
}

data class OnboardingItem(
    val title: String,
    val description: String,
    val illustration: OnboardingIllustration,
    val buttonText: String
)
