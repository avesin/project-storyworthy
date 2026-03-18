package com.jadigaknih.storyworthy.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.jadigaknih.storyworthy.base.toRange
import com.jadigaknih.storyworthy.data.local.dao.StoryDao
import com.jadigaknih.storyworthy.data.local.entity.StoryEntity
import com.jadigaknih.storyworthy.data.model.StoryCountPerDay
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val dao: StoryDao
) {

    val stories = dao.getStories()

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getStories(yearMonth: YearMonth): Flow<List<StoryEntity>> {
        val (start, end) = yearMonth.toRange()
        return dao.getStories(start, end)
    }

    suspend fun getStories(range: Pair<Long, Long>): Flow<List<StoryEntity>> {
        return dao.getStories(range.first, range.second)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCountStories(yearMonth: YearMonth): Flow<List<StoryCountPerDay>> {
        val (start, end) = yearMonth.toRange()
        return dao.getCountStories(start, end)
    }

    suspend fun getCountStories(range: Pair<Long, Long>): Flow<List<StoryCountPerDay>> {
        val (start, end) = range
        return dao.getCountStories(start, end)
    }

    suspend fun insert(story: StoryEntity) {
        dao.insertStory(story)
    }

    suspend fun delete(story: StoryEntity) {
        dao.deleteStory(story)
    }
}