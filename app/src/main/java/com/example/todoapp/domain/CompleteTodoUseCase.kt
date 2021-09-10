package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository

class CompleteTodoUseCase(private val todoId: String) {
    private val repository = TodoRepository()

    suspend operator fun invoke(): Int = repository.completeTodo(todoId)
}