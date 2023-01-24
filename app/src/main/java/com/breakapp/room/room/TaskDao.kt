package com.breakapp.room.room


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT *  FROM task_entity")
    suspend fun getAllTask(): List<TaskEntity>
    //MutableList<TaskEntity>

    @Insert
    suspend fun addTask(taskEntity : TaskEntity)

    @Query("SELECT * FROM task_entity where id like :id")
    fun getTaskById(id: Long): TaskEntity

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity):Int
}