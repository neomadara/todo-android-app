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
import com.example.todoapp.data.model.TodoModel

@Composable
fun TodoScreen(
    todos: List<TodoModel>,
    onAddTodo: (String) -> Unit,
    isLoading: Boolean,
    onCompleteTodo: (String) -> Unit
) {
    if (isLoading) {
        LoadingComposable()
    } else {
        Column {
            Text("TODOS", Modifier.align(CenterHorizontally).padding(top = 8.dp))
            TodoInput(onAddTodo)
            TodoList(
                todos,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onComplete = onCompleteTodo
            )
        }
    }
}

@Composable
fun TodoList(todoList: List<TodoModel>, modifier: Modifier = Modifier, onComplete: (String) -> Unit) {
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
        TodoModel("1", "todo 1"),
        TodoModel("2", "todo 2"),
        TodoModel("3", "todo 3")
    )
    MaterialTheme {
        TodoScreen(todos = todos, {}, false, {})
    }
}
