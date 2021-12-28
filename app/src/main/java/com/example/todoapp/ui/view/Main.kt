package com.example.todoapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.todoapp.ui.viewmodel.TodoViewModel

@Composable
fun TodoMainScreen(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = true)
    val isSaving by viewModel.isSaving.observeAsState(initial = true)
    val isUpdating by viewModel.isUpdating.observeAsState(initial = true)

    TodoScreen(
        todos = todoList,
        onAddTodo = { viewModel.saveTodo(it) },
        onCompleteTodo = { viewModel.completeTodo(it) },
        isLoading = isLoading,
        isSaving = isSaving,
        isUpdating = isUpdating
    )
}