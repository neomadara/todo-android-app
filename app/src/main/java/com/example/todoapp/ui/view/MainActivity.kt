package com.example.todoapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoViewModel.getTodos()

        binding.composeView.setContent {
            MaterialTheme {
                TodoActivityScreen(todoViewModel)
            }
        }
    }
}

@Composable
fun TodoActivityScreen(todoViewModel: TodoViewModel) {
    val todoList by todoViewModel.todoList.observeAsState(initial = emptyList())
    val isLoading by todoViewModel.isLoading.observeAsState(initial = true)

    TodoScreen(
        todos = todoList,
        onAddTodo = { todoViewModel.saveTodo(it) },
        onCompleteTodo = { todoViewModel.completeTodo(it) },
        isLoading = isLoading
    )
}