package com.example.todoapp.domain

import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.model.TodoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(private val repository: TodoRepository){

    operator fun invoke(
        onError: (String) -> Unit
    ): Flow<List<TodoModel>> = repository.fetchTodos(
        onError = { onError(it) },
    )
}