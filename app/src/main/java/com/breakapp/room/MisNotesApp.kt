package com.breakapp.room

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.breakapp.room.room.TaskDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MisNotesApp: Application() {


    /*companion object{

        private var database: TaskDatabase?=null

        fun  getDataBase(context: Context): TaskDatabase{
            database = database ?: Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task-db"
            ).build()
            return database!!
        }

        suspend fun destroyInstance() {
           database = null
        }
    }*/

}