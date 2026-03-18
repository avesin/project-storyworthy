package com.jadigaknih.storyworthy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.jadigaknih.storyworthy.data.local.entity.StoryEntity
import com.jadigaknih.storyworthy.data.model.StoryCountPerDay
import kotlinx.coroutines.flow.Flow
import java.time.Month
import java.time.YearMonth

@Dao
interface StoryDao {

    @Query("SELECT * FROM stories  ORDER BY createdAt ASC")
    fun getStories(): Flow<List<StoryEntity>>


    @Query("""
        SELECT * FROM stories 
        WHERE date BETWEEN :start AND :end
        ORDER BY createdAt DESC
    """)
    fun getStories(start: Long, end: Long): Flow<List<StoryEntity>>

    @Query("""
        SELECT DATE(date/1000, 'unixepoch') as day, COUNT(*) as count
        FROM stories
        WHERE date >= :start AND date < :end
        GROUP BY day
        ORDER BY day
    """)
    fun getCountStories(start: Long, end: Long): Flow<List<StoryCountPerDay>>


    @Insert(onConflict = REPLACE)
    suspend fun insertStory(story: StoryEntity)

    @Delete
    suspend fun deleteStory(story: StoryEntity)
}