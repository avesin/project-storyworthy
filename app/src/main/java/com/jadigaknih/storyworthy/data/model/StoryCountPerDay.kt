package com.jadigaknih.storyworthy.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class StoryCountPerDay(
    val day: String,
    val count: Int
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun localDate(): LocalDate = LocalDate.parse(day)
}