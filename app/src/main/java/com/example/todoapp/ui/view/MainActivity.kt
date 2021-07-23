package com.example.todoapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        setupUI()
    }

    private fun setupUI() {
        todoViewModel.getTodos()

        todoViewModel.todoList.observe(this, {
            adapter.setTodo(it)
        })

        todoViewModel.isLoading.observe(this, {
            binding.progress.isVisible = it
        })

        todoViewModel.updateTodoList.observe(this, {
            if (it) todoViewModel.getTodos()
        })

        val dialog = CreateTodoDialogFragment()
        binding.floatingActionButton.setOnClickListener {
            todoViewModel.dismissDialog.postValue(false)
            dialog.show(supportFragmentManager, "createTodoDialog")
        }

        todoViewModel.showToastSuccessfully.observe(this, { showToastSuccessfully ->
            showToastSuccessfully.let {
                todoViewModel.showToastSuccessfully.value = false
                Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
            }
        })

        todoViewModel.showToastError.observe(this, { showToastError ->
            showToastError.let {
                todoViewModel.showToastError.value = false
                Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()
            }
        })
    }
}