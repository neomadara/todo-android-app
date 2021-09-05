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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.data.model.TodoModel

@Composable
fun TodoScreen(items: MutableLiveData<MutableList<TodoModel>>) {
    val todoName = remember { mutableStateOf(TextFieldValue()) }
    val todos = items

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
fun TodoList(todos: MutableLiveData<MutableList<TodoModel>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = todos) { todo ->
            TodoCard(todo)
        }
    }
}

@Composable
fun TodoCard(todo: String) {
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
            Text(todo)
        }
    }
}




@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TodoScreen(items = List(10) { "TODO $it" })
    }
}