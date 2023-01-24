package com.breakapp.room.viewmodel

import androidx.lifecycle.*
import com.breakapp.room.room.TaskEntity
import com.breakapp.room.Repository.TaskRepo
import com.breakapp.room.retrofit.RickMorty
import com.breakapp.room.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (private val taskRepo: TaskRepo) : ViewModel() {

    private val rickMortyData = MutableLiveData<String>()
    fun setRickMorty(rickMorty: String) {
        rickMortyData.value = rickMorty
    }

    init {
        setRickMorty("margarita")
    }

    val fetchRickList = rickMortyData.distinctUntilChanged().switchMap { rm ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(taskRepo.getRickMorty(rm))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchPokemon = rickMortyData.distinctUntilChanged().switchMap { rm ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(taskRepo.getAllPokemon())
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }


    fun guardarTrago(trago: TaskEntity) {
        viewModelScope.launch {
            taskRepo.addTask(trago)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            taskRepo.updateTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            taskRepo.deleteTask(task)
        }
    }

    fun getTasks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(taskRepo.getTaskList())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }


}
