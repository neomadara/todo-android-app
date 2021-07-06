package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.model.TodoModel

class SaveTodoUseCase(title: String) {
    private val todoTitle: String = title
    private val repository = TodoRepository()

    suspend operator fun invoke(): TodoModel? = repository.saveTodo(todoTitle)
}