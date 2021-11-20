package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.domain.CompleteTodoUseCase
import com.example.todoapp.domain.GetTodosUseCase
import com.example.todoapp.domain.SaveTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val saveTodoUseCase: SaveTodoUseCase,
    private val completeTodoUseCase: CompleteTodoUseCase,
) : ViewModel() {
    private val TAG = "TodoViewModel"

    private val _todoList = MutableLiveData<List<TodoModel>>()
    val todoList: LiveData<List<TodoModel>> get() = _todoList

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getTodosUseCase()
            Log.d(TAG, "result get todos use case $result")
            if(!result.isNullOrEmpty()){
                _todoList.postValue(result as List<TodoModel>?)
            }
            _isLoading.postValue(false)
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            val result = saveTodoUseCase(title)
            Log.d(TAG, "result use case -> $result")
            if (result is TodoModel) {
                Log.d(TAG, "save todo use case ok")
            }
            getTodos()
        }
    }

    fun completeTodo(todoId: String) {
        viewModelScope.launch {
            val result = completeTodoUseCase(todoId)
            Log.d(TAG, "result use case -> $result")
            getTodos()
        }
    }
}