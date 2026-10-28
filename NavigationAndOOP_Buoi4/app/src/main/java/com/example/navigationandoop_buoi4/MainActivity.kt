
package com.example.navigationandoop_buoi4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.navigationandoop_buoi4.navigation.AppNavigation
import com.example.navigationandoop_buoi4.ui.theme.NavigationAndOOP_Buoi4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationAndOOP_Buoi4Theme {
                AppNavigation()
            }
        }
    }
}
