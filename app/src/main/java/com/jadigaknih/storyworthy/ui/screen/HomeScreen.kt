package com.jadigaknih.storyworthy.ui.screen

import android.annotation.SuppressLint
import android.graphics.drawable.Animatable
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jadigaknih.storyworthy.base.toHourMinute
import com.jadigaknih.storyworthy.data.constants.PastelColors
import com.jadigaknih.storyworthy.ui.viewmodel.StoryViewModel
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

const val isDebug = false

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: StoryViewModel = hiltViewModel(),
    padding: PaddingValues,
    selectedDate: MutableState<LocalDate?>,
) {
    val groupStories by viewModel.countStories.collectAsState()
    val stories by viewModel.stories.collectAsState()
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val now = LocalDate.now()
    val formatterMMMMyyyy = DateTimeFormatter.ofPattern("MMMM yyyy")
    val formatteryyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val scope = rememberCoroutineScope()
    val state = rememberCalendarState(
        startMonth = YearMonth.now().minusYears(1),
        endMonth = YearMonth.now().plusYears(100),
        firstDayOfWeek = firstDayOfWeek,
        firstVisibleMonth = YearMonth.now(),
        outDateStyle = OutDateStyle.EndOfGrid,
    )
    viewModel.getCountStories(state.firstVisibleMonth.yearMonth)
    LaunchedEffect(selectedDate.value) {
        if (selectedDate.value != null) {
            viewModel.getStories(selectedDate.value!!)
        }
    }
    LazyColumn(
        Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        item {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            val previous = state.firstVisibleMonth.yearMonth.minusMonths(1)
                            state.animateScrollToMonth(previous)
                        }
                    }, modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous Month"
                    )
                }
                Text(
                    "${state.firstVisibleMonth.yearMonth.format(formatterMMMMyyyy)}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        scope.launch {
                            val next = state.firstVisibleMonth.yearMonth.plusMonths(1)
                            state.animateScrollToMonth(next)
                        }
                    }, modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next Month"
                    )
                }
            }
        }
        item {
            HorizontalCalendar(state = state, dayContent = { date ->
                Day(
                    count = if (isDebug) (0..12).random() else groupStories.find {
                        it.day == date.date.format(formatteryyyyMMdd)
                    }?.count ?: 0,
                    now,
                    state.firstVisibleMonth.yearMonth,
                    date,
                    selectedDate = selectedDate.value,
                    onClick = {
                        if (selectedDate == it) selectedDate.value = null else selectedDate.value =
                            it
                    }
                )
            })
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(stories.size, key = { stories[it].id }) { index ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.CenterEnd,
                ) {

                    Card(
                        onClick = {
                            scope.launch {
                                selectedDate.value = selectedDate.value?.plusDays(0)
                                viewModel.delete(stories[index])
                            }
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.matchParentSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 16.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                fontWeight = FontWeight.Bold,
                                text = "Delete",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    }

                    SwipeCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "[ ${stories[index].createdAt.toHourMinute()} ]",
                                textAlign = TextAlign.Start
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stories[index].content,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }

    }
}
@Composable
fun SwipeCard(
    modifier: Modifier,
    shape: androidx.compose.ui.graphics.Shape,
    content: @Composable ColumnScope.() -> Unit
) {
    val offsetX = remember { androidx.compose.animation.core.Animatable(0f) }
    val scope = rememberCoroutineScope()
    val maxSwipe = -250f
    val threshold = -50f
    Card(
        modifier = modifier
            .fillMaxWidth()
            .offset { IntOffset(offsetX.value.toInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onHorizontalDrag = { _, dragAmount ->
                        val newOffset =
                            (offsetX.value + dragAmount)
                                .coerceIn(maxSwipe, 0f)
                        scope.launch {
                            offsetX.snapTo(newOffset)
                        }
                    },
                    onDragEnd = {
                        scope.launch {
                            if (offsetX.value < threshold) {
                                // auto swipe kiri
                                offsetX.animateTo(maxSwipe)
                            } else {
                                // kembali ke awal
                                offsetX.animateTo(0f)
                            }
                        }
                    }
                )
            },
        shape = shape,
        content = content
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Day(
    count: Int,
    now: LocalDate,
    firstVisibleMonth: YearMonth,
    day: CalendarDay,
    selectedDate: LocalDate?,
    onClick: (date: LocalDate) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(if (selectedDate == day.date) Color.Blue.copy(alpha = 0.1f) else Color.Transparent)
            .clickable {
                onClick(day.date)
            }
            .aspectRatio(0.7f)
            .border(
                width = if (day.date == now) 1.dp else 0.1.dp,
                color = if (day.date == now) Color.Blue.copy(alpha = 0.5f) else Color.LightGray
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                text = day.date.dayOfMonth.toString(),
                color = if (day.date.month == firstVisibleMonth.month) Color.Black else Color.Gray,
                fontWeight = if (day.date == now) FontWeight.ExtraBold else if (day.date.month == firstVisibleMonth.month) FontWeight.Light else FontWeight.Thin
            )
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                reverseLayout = false,
                columns = GridCells.Fixed(6)
            ) {
                items(count) { key ->
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "",
                        tint = PastelColors[key]
                    )
                }
            }
        }
    }
}