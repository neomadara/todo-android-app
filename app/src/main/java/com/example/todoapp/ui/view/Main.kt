package com.example.todoapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.todoapp.ui.viewmodel.TodoViewModel

@Composable
fun TodoMainScreen(viewModel: TodoViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    TodoScreen(
        uiState = uiState,
        onAddTodo = { viewModel.saveTodo(it) },
        onCompleteTodo = { viewModel.completeTodo(it) },
    )
}