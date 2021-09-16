package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.model.TodoModel
import javax.inject.Inject

class SaveTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(title: String): TodoModel? = repository.saveTodo(title)
}