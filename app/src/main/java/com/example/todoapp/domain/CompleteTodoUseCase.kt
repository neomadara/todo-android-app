package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import javax.inject.Inject

class CompleteTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(todoId: String): Int = repository.completeTodo(todoId)
}