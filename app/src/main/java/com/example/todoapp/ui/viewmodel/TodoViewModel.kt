package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.Todo
import com.example.todoapp.domain.CompleteTodoUseCase
import com.example.todoapp.domain.GetTodosUseCase
import com.example.todoapp.domain.SaveTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TodoUiState {
    val isLoading: Boolean
    val isSaving: Boolean
    val isUpdating: Boolean
    val errorMessage: String

    data class NoTodos(
        override val isLoading: Boolean,
        override val isSaving: Boolean,
        override val isUpdating: Boolean,
        override val errorMessage: String
    ) : TodoUiState

    data class HasTodos(
        val todos: List<Todo>,
        override val isLoading: Boolean,
        override val isSaving: Boolean,
        override val isUpdating: Boolean,
        override val errorMessage: String
    ): TodoUiState
}

private data class TodoViewModelState(
    val todos: List<Todo> = emptyList(),
    val isLoading: Boolean = true,
    val isSaving: Boolean = false,
    val isUpdating: Boolean = false,
    val errorMessage: String = ""
) {
    fun toUiState(): TodoUiState =
        if (todos.isEmpty()) {
            TodoUiState.NoTodos(
                isLoading = isLoading,
                isSaving = isSaving,
                isUpdating = isUpdating,
                errorMessage = errorMessage

            )
        } else {
            TodoUiState.HasTodos(
                todos = todos,
                isLoading = isLoading,
                isSaving = isSaving,
                isUpdating = isUpdating,
                errorMessage = errorMessage
            )
        }
}


@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val saveTodoUseCase: SaveTodoUseCase,
    private val completeTodoUseCase: CompleteTodoUseCase,
) : ViewModel() {
    private val TAG = "TodoViewModel"

    private val viewModelState = MutableStateFlow(TodoViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        fetchTodos(
            onStart = { viewModelState.update { it.copy(isLoading = true) } },
            onCompletion = { viewModelState.update { it.copy(isLoading = false) } }
        )
    }

    private fun fetchTodos(onStart: () -> Unit, onCompletion: () -> Unit) {
        viewModelScope.launch {
            getTodosUseCase(
                onError = { Log.e("ERROR", it) }
            )
                .onStart { onStart() }
                .onCompletion { onCompletion() }
                .collect { todoList ->
                    Log.d(TAG, "result get todos use case $todoList")
                    viewModelState.update {
                        it.copy(todos = todoList)
                    }
                }
        }
    }

    fun saveTodo(title: String) {
        viewModelScope.launch {
            val result = saveTodoUseCase(title)
            Log.d(TAG, "result use case -> $result")
            if (result is Todo) {
                Log.d(TAG, "save todo use case ok")
            }
            fetchTodos(
                onStart = { viewModelState.update {
                    it.copy(isSaving = true)
                } },
                onCompletion = { viewModelState.update {
                    it.copy(isSaving = false)
                } }
            )
        }
    }

    fun completeTodo(todoId: String) {
        viewModelScope.launch {
            val result = completeTodoUseCase(todoId)
            Log.d(TAG, "result use case -> $result")
            fetchTodos(
                onStart = { viewModelState.update {
                    it.copy(isUpdating = true)
                } },
                onCompletion = { viewModelState.update {
                    it.copy(isUpdating = false)
                } }
            )
        }
    }
}