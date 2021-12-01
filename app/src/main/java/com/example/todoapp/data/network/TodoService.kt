package com.example.todoapp.data.network

import android.util.Log
import com.example.todoapp.data.model.TodoModel
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class TodoService @Inject constructor(private val api:TodoApiClient){
    private val TAG = "TodoService"

    suspend fun getTodos(): ApiResponse<List<TodoModel>> {
        return api.getAllTodos()
    }

    suspend fun saveTodo(todo: TodoModel): TodoModel? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "todo model -> $todo")

            val jsonObject = JSONObject()
            jsonObject.put("title", todo.title)
            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            val response = api.saveTodo(requestBody)

            Log.d(TAG, "response -> $response")

            when (response.code()) {
                201 -> Log.d(TAG, "response service")
                else -> Log.d(TAG, "response service error")
            }

            response.body()
        }
    }

    suspend fun completeTodo(todoId: String): Int {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "todo complete ${todoId}")
            val response = api.completedTodo(todoId)

            Log.d(TAG, "response -> $response")

            return@withContext response.code()
        }
    }


}