package com.jadigaknih.storyworthy.ui.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadigaknih.storyworthy.base.toEpochWithCurrentTime
import com.jadigaknih.storyworthy.data.local.entity.StoryEntity
import com.jadigaknih.storyworthy.data.model.StoryCountPerDay
import com.jadigaknih.storyworthy.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val repository: StoryRepository
) : ViewModel() {
    private val _countStories = MutableStateFlow<List<StoryCountPerDay>>(emptyList())
    val countStories: StateFlow<List<StoryCountPerDay>> = _countStories
    private val _stories = MutableStateFlow<List<StoryEntity>>(emptyList())
    val stories: StateFlow<List<StoryEntity>> = _stories

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCountStories(yearMonth: YearMonth) {
        viewModelScope.launch {
            repository.getCountStories(yearMonth)
                .collect {
                    _countStories.value = it
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getStories(date: LocalDate) {
        val milisStart = date
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        val millisEnd = date
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli() - 1
        val range = milisStart to millisEnd
        viewModelScope.launch {
            repository.getStories(range)
                .collect {
                    _stories.value = it
                }
        }
    }

    fun delete(data: StoryEntity){
        viewModelScope.launch {
            repository.delete(data)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveStory(text: String, selectedDate: LocalDate?) {
        val date =  if(selectedDate == null) System.currentTimeMillis() else if(selectedDate == LocalDate.now()) System.currentTimeMillis() else selectedDate.toEpochWithCurrentTime()
        viewModelScope.launch {
            repository.insert(
                StoryEntity(
                    content = text,
                    date = date,
                    createdAt = System.currentTimeMillis(),
                )
            )
        }
    }

}