package com.jadigaknih.storyworthy.di

import android.content.Context
import androidx.room.Room
import com.jadigaknih.storyworthy.data.local.AppDatabase
import com.jadigaknih.storyworthy.data.local.dao.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "storyworthy.db"
        ).build()
    }

    @Provides
    fun provideStoryDao(
        db: AppDatabase
    ): StoryDao {
        return db.storyDao()
    }
}