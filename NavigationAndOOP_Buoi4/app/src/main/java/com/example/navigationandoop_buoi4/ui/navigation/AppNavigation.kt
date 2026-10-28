
package com.example.navigationandoop_buoi4.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import com.example.navigationandoop_buoi4.data.OnboardingRepository
import com.example.navigationandoop_buoi4.domain.OnboardingUseCase
import com.example.navigationandoop_buoi4.ui.screens.OnboardingScreen
import com.example.navigationandoop_buoi4.ui.screens.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val repository = OnboardingRepository()
    val useCase = OnboardingUseCase(repository)

    NavHost(
        navController = navController,
        startDestination = AppDestination.SPLASH
    ) {
        composable(AppDestination.SPLASH) {
            LaunchedEffect(Unit) {
                delay(2500)
                navController.navigate(AppDestination.ONBOARDING) {
                    popUpTo(AppDestination.SPLASH) {
                        inclusive = true
                    }
                }
            }

            SplashScreen()
        }

        composable(AppDestination.ONBOARDING) {
            OnboardingScreen(
                useCase = useCase,
                onFinished = {
                    navController.navigate(AppDestination.HOME) {
                        popUpTo(AppDestination.ONBOARDING) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppDestination.HOME) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("UTH Todo - Trang chủ")
            }
        }
    }
}
