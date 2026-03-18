package com.jadigaknih.storyworthy.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jadigaknih.storyworthy.base.toFormat
import com.jadigaknih.storyworthy.data.constants.PastelColors
import com.jadigaknih.storyworthy.ui.viewmodel.StoryViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStoryScreen(
    viewModel: StoryViewModel = hiltViewModel(),
    onSaved: () -> Unit,
    selectedDate: LocalDate?
) {
    var text by remember { mutableStateOf("") }
    val color = PastelColors.random()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                "Story on ${
                    selectedDate?.toFormat("dd, MMMM yyyy") ?: LocalDate.now()
                        .toFormat("dd, MMMM yyyy")
                }"
            )
            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                value = text, onValueChange = { text = it },
                label = { Text("Your story") },
                modifier = Modifier.fillMaxWidth(), minLines = 8
            )
            Spacer(Modifier.height(32.dp))
            Button(
                enabled = text.length > 1,
                colors = ButtonDefaults.buttonColors(
                    containerColor = color
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    viewModel.saveStory(text, selectedDate)
                    onSaved()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text("Add Story")
            }
        }
    }
}