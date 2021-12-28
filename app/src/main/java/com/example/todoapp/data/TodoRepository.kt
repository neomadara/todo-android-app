package com.example.todoapp.data

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
    fun fetchTodos(
        onError: (String) -> Unit
    ) = flow {
        api.getTodos()
            .suspendOnSuccess {
                emit(data)
            }
            .onError {
                onError(message())
            }
            .onException {
                onError(message())
            }
    }.flowOn(Dispatchers.IO)

    suspend fun saveTodo(todoTitle: String): TodoModel? {
        val todoObj = TodoModel(title = todoTitle)
        return api.saveTodo(todoObj)
    }

    suspend fun completeTodo(todoId: String): Int {
        return api.completeTodo(todoId)
    }
}