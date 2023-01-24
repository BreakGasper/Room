package com.breakapp.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.breakapp.room.Repository.TaskRepo

class TaskViewModelFactory (private val task: TaskRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TaskRepo::class.java).newInstance(task)
    }



}