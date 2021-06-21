package com.example.todoapp.data.network

import com.example.todoapp.core.RetrofitHelper
import com.example.todoapp.data.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getTodos():List<TodoModel>{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TodoApiClient::class.java).getAllTodos()
            response.body() ?: emptyList()
        }
    }
}