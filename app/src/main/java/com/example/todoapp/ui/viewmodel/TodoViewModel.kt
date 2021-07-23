package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.domain.GetTodosUseCase
import com.example.todoapp.domain.SaveTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    private val TAG = "TodoViewModel"
    val todoList = MutableLiveData<MutableList<TodoModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val showToastSuccessfully = MutableLiveData<Boolean>()
    val showToastError = MutableLiveData<Boolean>()
    val dismissDialog = MutableLiveData<Boolean>()
    val updateTodoList = MutableLiveData<Boolean>()

    var getTodosUseCase = GetTodosUseCase()

    fun getTodos() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTodosUseCase()
            
            if(!result.isNullOrEmpty()){
                todoList.postValue(result as MutableList<TodoModel>?)
                isLoading.postValue(false)
            }
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            val saveTodoUseCase = SaveTodoUseCase(title)
            val result = saveTodoUseCase()
            Log.d(TAG, "result use case -> $result")
            if (result is TodoModel) {
                Log.d(TAG, "todoList -> $todoList")
                showToastSuccessfully.postValue(true)
            } else {
                showToastError.postValue(true)
            }
            dismissDialog.postValue(true)
        }
    }
}