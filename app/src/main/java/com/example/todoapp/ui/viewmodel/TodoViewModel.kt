package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.domain.GetTodosUseCase
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    // val todoModel = MutableLiveData<TodoModel>()
    val todoList = MutableLiveData<List<TodoModel>>()
    val isLoading = MutableLiveData<Boolean>()

    var getTodosUseCase = GetTodosUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTodosUseCase()
            
            if(!result.isNullOrEmpty()){
                // todoModel.postValue(result)
                todoList.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}