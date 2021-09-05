package com.example.todoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.model.TodoModel

@Composable
fun TodoScreen(todos: List<TodoModel>) {
    val todoName = remember { mutableStateOf(TextFieldValue()) }

    Column {
        Text("TODOS", Modifier.align(CenterHorizontally))
        TextField(
            label = { Text("Enter a TODO") },
            value = todoName.value,
            onValueChange = { todoName.value = it },
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp
                )
        )
        TodoList(todos, modifier = Modifier.weight(1f))
    }
}

@Composable
fun TodoList(todoList: List<TodoModel>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = todoList) { todo ->
            TodoCard(todo)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    val todo = TodoModel("", "todo 1")
    val todos = List(1) { todo }
    MaterialTheme {
        TodoScreen(todos = todos)
    }
}
