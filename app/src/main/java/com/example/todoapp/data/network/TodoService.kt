package com.example.todoapp.data.network

import android.util.Log
import com.example.todoapp.core.RetrofitHelper
import com.example.todoapp.data.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class TodoService {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val TAG = "TodoService"

    suspend fun getTodos():List<TodoModel>{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TodoApiClient::class.java).getAllTodos()
            Log.d(TAG, "getTodos services -> ${response.body() ?: emptyList()}")
            response.body() ?: emptyList()
        }
    }

    suspend fun saveTodo(todo: TodoModel): TodoModel? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "todo model -> $todo")

            val jsonObject = JSONObject()
            jsonObject.put("title", todo.title)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val response = retrofit.create(TodoApiClient::class.java).saveTodo(requestBody)

            Log.d(TAG, "response -> $response")

            when (response.code()) { // TODO: refactor this
                201 -> Log.d(TAG, "response service")
                else -> Log.d(TAG, "response service error")
            }

            response.body()
        }
    }
}