package com.jadigaknih.storyworthy.base

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun YearMonth.toRange(): Pair<Long, Long> {

    val start = this.atDay(1)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    val end = this.atEndOfMonth()
        .atTime(LocalTime.MAX)
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    return start to end
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toHourMinute(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toFormat(format: String): String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toEpochWithCurrentTime(): Long {
    return this
        .atTime(LocalTime.now())
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
}