package com.example.todoapp.data.network

import com.example.todoapp.data.model.TodoModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoApiClient {
    @GET("/api/todos")
    suspend fun getAllTodos(): Response<List<TodoModel>>

    @POST("/api/todos")
    suspend fun saveTodo(@Body requestBody: RequestBody): Response<TodoModel>
}