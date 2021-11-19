package com.example.todoapp.data

import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.data.network.TodoService
import javax.inject.Inject

class TodoRepository @Inject constructor(private val api:TodoService) {
    suspend fun getAllTodos(): List<TodoModel>{
        return api.getTodos()
    }

    suspend fun saveTodo(todoTitle: String): TodoModel? {
        val todoObj = TodoModel(title = todoTitle)
        return api.saveTodo(todoObj)
    }

    suspend fun completeTodo(todoId: String): Int {
        return api.completeTodo(todoId)
    }
}