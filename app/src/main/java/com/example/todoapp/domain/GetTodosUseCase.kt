package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.model.TodoModel
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(private val repository: TodoRepository){

    suspend operator fun invoke():List<TodoModel>? = repository.getAllTodos()
}