package com.breakapp.room.Repository

import com.breakapp.room.retrofit.Pokemon
import com.breakapp.room.retrofit.RickMorty
import com.breakapp.room.room.TaskEntity
import com.breakapp.room.vo.Resource

interface TaskRepo {
      suspend fun getRickMorty(rickMorty: String):Resource<List<RickMorty>>
      suspend fun getAllPokemon():Resource<List<Pokemon>>

      suspend fun getTaskList() : Resource<List<TaskEntity>>
      suspend fun addTask(task: TaskEntity)
      suspend fun deleteTask(task: TaskEntity)
      suspend fun updateTask(task: TaskEntity)
}