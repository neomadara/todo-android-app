package com.example.todoapp.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.adapter.TodoRecyclerAdapter
import com.example.todoapp.ui.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val todoViewModel: TodoViewModel by viewModels()
    private val adapter = TodoRecyclerAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTodos.adapter = adapter

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        todoViewModel.getTodos()

        binding.etTodoTitle.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    Log.d(TAG, "done")
                    todoViewModel.saveTodo(binding.etTodoTitle.text.toString())
                    binding.etTodoTitle.setText("")
                    val imm: InputMethodManager =
                        v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0); true
                }
                else -> false
            }
        }
    }

    private fun setupObservers() {
        todoViewModel.todoList.observe(this, {
            adapter.setTodo(it)
        })

        todoViewModel.isLoading.observe(this, {
            binding.progress.isVisible = it
        })
    }
}