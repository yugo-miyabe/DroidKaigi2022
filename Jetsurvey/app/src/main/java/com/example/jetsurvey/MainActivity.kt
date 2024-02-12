package com.example.jetsurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jetsurvey.ui.theme.JetsurveyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 画面いっぱいに広げる
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JetsurveyTheme {
                JetsurveyNavHost()
            }
        }
    }
}
