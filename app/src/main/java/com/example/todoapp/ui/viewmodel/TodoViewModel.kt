package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.domain.GetTodosUseCase
import com.example.todoapp.domain.SaveTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    private val TAG = "TodoViewModel"

    private val _todoList = MutableLiveData<MutableList<TodoModel>>()
    val todoListLiveData: LiveData<MutableList<TodoModel>> = _todoList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoading
    // private val _dismissDialog = MutableLiveData<Boolean>()
    // val dismissDialog: LiveData<Boolean> get() = _dismissDialog

    var getTodosUseCase = GetTodosUseCase()

    fun getTodos() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getTodosUseCase()
            Log.d(TAG, "result get todos use case $result")
            if(!result.isNullOrEmpty()){
                _todoList.postValue(result as MutableList<TodoModel>?)
            }
            _isLoading.postValue(false)
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            val saveTodoUseCase = SaveTodoUseCase(title)
            val result = saveTodoUseCase()
            Log.d(TAG, "result use case -> $result")
            if (result is TodoModel) {
                Log.d(TAG, "save todo use case ok")
            }
            getTodos()
        }
    }
}