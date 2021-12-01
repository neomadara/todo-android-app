package com.example.todoapp.data

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.data.network.TodoService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private val api:TodoService) {

    @WorkerThread
    fun getAllTodos() = flow {
        api.getTodos()
            .suspendOnSuccess {
                emit(data)
            }
            .onError { Log.e("error", message()) }
            .onException { Log.e("error", message()) }
    }.flowOn(Dispatchers.IO)

    suspend fun saveTodo(todoTitle: String): TodoModel? {
        val todoObj = TodoModel(title = todoTitle)
        return api.saveTodo(todoObj)
    }

    suspend fun completeTodo(todoId: String): Int {
        return api.completeTodo(todoId)
    }
}