package com.jadigaknih.storyworthy.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material.navigation.bottomSheet
import androidx.compose.material.navigation.rememberBottomSheetNavigator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jadigaknih.storyworthy.data.constants.PastelColors
import com.jadigaknih.storyworthy.ui.screen.AddStoryScreen
import com.jadigaknih.storyworthy.ui.screen.HomeScreen
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(
        bottomSheetNavigator
    )
    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator
    ) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {

            composable("home") {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            containerColor = PastelColors.random(),
                            shape = FloatingActionButtonDefaults.largeShape,
                            onClick = { navController.navigate("add") }
                        ) {
                            Icon(
                                tint = Color.White,
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add"
                            )
                        }
                    }
                ) { padding ->
                    HomeScreen(
                        padding = padding,
                        selectedDate = selectedDate
                    )
                }
            }
            bottomSheet("add") {
                AddStoryScreen(onSaved = {
                    navController.popBackStack()
                }, selectedDate = selectedDate.value)
            }
        }
    }
}