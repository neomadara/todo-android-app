package com.example.todoapp.data

import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.data.model.TodoProvider
import com.example.todoapp.data.network.TodoService

class TodoRepository {
    private val api = TodoService()

    suspend fun getAllTodos(): List<TodoModel>{
        val response = api.getTodos()
        TodoProvider.todos = response as MutableList<TodoModel>
        return response
    }

    /*suspend fun saveTodo(todo: TodoModel): TodoModel {
        val response = api.saveTodo(todo)
    }*/
}