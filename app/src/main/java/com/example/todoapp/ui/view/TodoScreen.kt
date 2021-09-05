package com.example.todoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.todoapp.data.model.TodoModel

@Composable
fun TodoScreen(todos: LiveData<List<TodoModel>>) {
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
fun TodoList(todoListLiveData: LiveData<List<TodoModel>>, modifier: Modifier = Modifier) {
    val todoList by todoListLiveData.observeAsState(initial = emptyList())

    LazyColumn(modifier = modifier) {
        items(items = todoList) { todo ->
            TodoCard(todo)
        }
    }
}

@Composable
fun TodoCard(todo: TodoModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp),
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(todo.title)
        }
    }
}




/*
@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TodoScreen()
    }
}*/
