package com.example.todoapp.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun TodoInput(onAddTodo: (String) -> Unit){
    val todoState = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        isError = todoState.value.toString().isEmpty(),
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
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp
            )
    )
}