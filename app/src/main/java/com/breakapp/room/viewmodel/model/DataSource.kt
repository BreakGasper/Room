package com.breakapp.room.viewmodel.model

import com.breakapp.room.retrofit.Pokemon
import com.breakapp.room.retrofit.RickMorty
import com.breakapp.room.room.TaskDatabase
import com.breakapp.room.room.TaskEntity
import com.breakapp.room.vo.Resource
import com.breakapp.room.vo.RetrofitClient
import javax.inject.Inject

class DataSource @Inject constructor(private val appDataBase: TaskDatabase) {
    suspend fun  getRickMorty(rm:String): Resource<List<RickMorty>>{
        return Resource.Success(RetrofitClient.webservice.getRickMorty(rm).drinkList)
    }

    suspend fun  getAllPokemon(): Resource<List<Pokemon>>{
        return Resource.Success(RetrofitClient.webservicepokemon.getAllPokemon().pokemonList)
    }

    suspend fun insertTaskList(taskInsert:TaskEntity){
        appDataBase.taskDao().addTask(taskInsert)
    }

   suspend fun getTaskList(): Resource<List<TaskEntity>> {
        return Resource.Success(appDataBase.taskDao().getAllTask())

    }

    suspend fun deleteTask(task: TaskEntity){
        appDataBase.taskDao().deleteTask(task)
    }
    suspend fun updateTask(task: TaskEntity){
        appDataBase.taskDao().updateTask(task)
    }


}