package com.example.todoapp.data.network

import com.example.todoapp.data.model.TodoModel
import retrofit2.Response
import retrofit2.http.GET

interface TodoApiClient {
    @GET("/todos")
    suspend fun getAllTodos(): Response<List<TodoModel>>
}