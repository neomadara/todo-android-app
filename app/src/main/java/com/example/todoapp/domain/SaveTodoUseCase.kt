package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.model.Todo
import javax.inject.Inject

class SaveTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(title: String): Todo? = repository.saveTodo(title)
}