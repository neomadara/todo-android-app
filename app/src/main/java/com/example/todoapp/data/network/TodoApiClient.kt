package com.example.todoapp.data.network

import com.example.todoapp.data.model.Todo
import com.skydoves.sandwich.ApiResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface TodoApiClient {
    @GET("/api/todos")
    suspend fun getAllTodos(): ApiResponse<List<Todo>>

    @POST("/api/todos")
    suspend fun saveTodo(@Body requestBody: RequestBody): Response<Todo>

    @PUT("/api/todos/{id}/completed")
    suspend fun completedTodo(@Path("id") todoID: String): Response<Void>
}