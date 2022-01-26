package com.example.todoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.model.Todo
import com.example.todoapp.ui.viewmodel.TodoUiState

@Composable
fun TodoScreen(
    uiState: TodoUiState,
    onAddTodo: (String) -> Unit,
    onCompleteTodo: (String) -> Unit
) {
    if (uiState.isLoading) {
        LoadingComposable()
    } else {
        val todosListState = when (uiState) {
            is TodoUiState.HasTodos -> uiState.todos
            is TodoUiState.NoTodos -> emptyList()
        }
        Column {
            Text("TODOS", Modifier.align(CenterHorizontally).padding(top = 8.dp))
            TodoInput(onAddTodo)
            TodoList(
                todosListState,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onComplete = onCompleteTodo
            )
        }
        if(uiState.isUpdating) {
            Toast("Updating TODOS...")
        }
        if(uiState.isSaving) {
            Toast("Saving TODO...")
        }
    }
}

@Composable
fun TodoList(todoList: List<Todo>, modifier: Modifier = Modifier, onComplete: (String) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(items = todoList) { todo ->
            TodoCard(todo, onComplete)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    val todos = listOf(
        Todo("1", "todo 1"),
        Todo("2", "todo 2"),
        Todo("3", "todo 3")
    )
    val uiState: TodoUiState = TodoUiState.HasTodos(
        todos = todos,
        isLoading = false,
        isSaving = false,
        isUpdating = false,
        errorMessage = ""
    )
    MaterialTheme {
        TodoScreen(
            uiState = uiState,
            onAddTodo = {},
            onCompleteTodo = {}
        )
    }
}
