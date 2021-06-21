package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.model.TodoModel

class GetTodosUseCase {
    private val repository = TodoRepository()

    suspend operator fun invoke():List<TodoModel>? = repository.getAllTodos()
}