package com.example.todoapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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
    }

    private fun setupUI() {
        todoViewModel.getTodos()

        todoViewModel.todoListLiveData.observe(this, {
            adapter.setTodo(it)
        })

        todoViewModel.isLoadingLiveData.observe(this, {
            binding.progress.isVisible = it
        })

        val dialog = CreateTodoDialogFragment()
        binding.floatingActionButton.setOnClickListener {
            dialog.show(supportFragmentManager, "createTodoDialog")
        }

        todoViewModel.dismissDialog.observe(this, {
            dialog.dismiss()
        })

        todoViewModel.dismissDialog.observe(this, {
            if(it) {
                Log.d(TAG, "get todos triggerd!")
                todoViewModel.getTodos()
            }
        })

    }
}