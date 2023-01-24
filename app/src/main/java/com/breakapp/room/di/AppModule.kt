package com.breakapp.room.di

import android.content.Context
import androidx.room.Room
import com.breakapp.room.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TaskDatabase::class.java,
        "task-db"
    ).build()

    @Provides
    @Singleton
    fun provideTaskDao(db:TaskDatabase) = db.taskDao()
}