package com.jadigaknih.storyworthy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jadigaknih.storyworthy.ui.navigation.AppNavigation
import com.jadigaknih.storyworthy.ui.theme.StoryworthyTheme
import androidx.annotation.RequiresApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            StoryworthyTheme {
                AppNavigation()
            }
        }
    }
}