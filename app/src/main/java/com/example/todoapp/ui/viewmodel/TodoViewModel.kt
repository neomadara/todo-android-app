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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
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
    
    private val _isSaving = MutableLiveData(false)
    val isSaving: LiveData<Boolean> get() = _isSaving

    private val _isUpdating = MutableLiveData(false)
    val isUpdating: LiveData<Boolean> get() = _isUpdating

    init {
        fetchTodos( 
            onStart = { _isLoading.postValue(true) }, 
            onCompletion = {_isLoading.postValue(false)} 
        )
    }

    private fun fetchTodos(onStart: () -> Unit, onCompletion: () -> Unit,) {
        viewModelScope.launch {
            getTodosUseCase(
                onError = { Log.e("ERROR", it) }
            )
                .onStart { onStart() }
                .onCompletion { onCompletion() }
                .collect { todoList ->
                    Log.d(TAG, "result get todos use case $todoList")
                    _todoList.postValue(todoList)
                }
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            val result = saveTodoUseCase(title)
            Log.d(TAG, "result use case -> $result")
            if (result is TodoModel) {
                Log.d(TAG, "save todo use case ok")
            }
            fetchTodos(onStart = { _isSaving.postValue(true) }, onCompletion = { _isSaving.postValue(false) })
        }
    }

    fun completeTodo(todoId: String) {
        viewModelScope.launch {
            val result = completeTodoUseCase(todoId)
            Log.d(TAG, "result use case -> $result")
            fetchTodos(onStart = { _isUpdating.postValue(true) }, onCompletion = { _isUpdating.postValue(false) })
        }
    }
}