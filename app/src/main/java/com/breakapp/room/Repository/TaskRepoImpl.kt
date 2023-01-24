package com.breakapp.room.Repository

import com.breakapp.room.retrofit.Pokemon
import com.breakapp.room.retrofit.RickMorty
import com.breakapp.room.viewmodel.model.DataSource
import com.breakapp.room.room.TaskEntity
import com.breakapp.room.vo.Resource
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(private val dataSource: DataSource): TaskRepo {

    override suspend fun getRickMorty(rickMorty: String): Resource<List<RickMorty>> {
        return dataSource.getRickMorty(rickMorty)
    }

    override suspend fun getAllPokemon(): Resource<List<Pokemon>> {
        return dataSource.getAllPokemon()
    }

    override suspend fun getTaskList(): Resource<List<TaskEntity>> {
        return dataSource.getTaskList()
    }

    override suspend fun addTask(task: TaskEntity) {
        dataSource.insertTaskList(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        dataSource.deleteTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        dataSource.updateTask(task)
    }
}