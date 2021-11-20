package com.example.todoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.data.model.TodoModel

@Composable
fun TodoCard(todo: TodoModel, onComplete: (String) -> Unit) {
    val checkedState = remember { mutableStateOf(todo.completed) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                todo.title,
                modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp),
                fontSize = 16.sp,
                textDecoration = if (checkedState.value) TextDecoration.LineThrough else TextDecoration.None
            )
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    onComplete(todo._id)
                },
                enabled = !todo.completed
            )
        }
    }
}

@Preview
@Composable
fun TodoCardPreview() {
    val todo = TodoModel("", "todo 1", true)
    MaterialTheme {
        TodoCard(todo, {})
    }
}