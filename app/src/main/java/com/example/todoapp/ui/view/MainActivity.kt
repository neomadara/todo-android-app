package com.example.todoapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.adapter.TodoRecyclerAdapter
import com.example.todoapp.ui.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {
    private val todoViewModel: TodoViewModel by viewModels()
    private val adapter = TodoRecyclerAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTodos.adapter = adapter

        todoViewModel.onCreate()

        todoViewModel.todoList.observe(this, {
            adapter.setTodo(it)
        })

        todoViewModel.isLoading.observe(this, {
            binding.progress.isVisible = it
        })

        binding.floatingActionButton.setOnClickListener {
            val dialog = CreateTodoDialogFragment()
            dialog.show(supportFragmentManager, "createTodoDialog")
        }

    }
}