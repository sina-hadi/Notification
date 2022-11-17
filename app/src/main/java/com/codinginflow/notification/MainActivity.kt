package com.codinginflow.notification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.codinginflow.notification.navigation.SetupNavGraph
import com.codinginflow.notification.ui.theme.NotificationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationTheme {
                SetupNavGraph(navController = rememberNavController())
            }
        }
    }
}