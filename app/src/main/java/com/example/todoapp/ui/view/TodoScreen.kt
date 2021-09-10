package com.example.todoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
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
    val todoState = remember { mutableStateOf(TextFieldValue()) }

    if (isLoading) {
        Box(
            modifier = Modifier.width(100.dp).height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(8.dp),
                strokeWidth = 12.dp,
            )
        }
    } else {
        Column {
            Text("TODOS", Modifier.align(CenterHorizontally).padding(top = 8.dp))
            TextField(
                label = { Text("Enter a TODO") },
                value = todoState.value,
                onValueChange = { todoState.value = it },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    onAddTodo(todoState.value.text)
                    todoState.value = TextFieldValue()
                }),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 8.dp
                    )
            )
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
