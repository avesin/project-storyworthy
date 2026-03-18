package com.jadigaknih.storyworthy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jadigaknih.storyworthy.data.local.dao.StoryDao
import com.jadigaknih.storyworthy.data.local.entity.StoryEntity

@Database(
    entities = [StoryEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}