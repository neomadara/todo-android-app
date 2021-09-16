package com.example.todoapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoProvider @Inject constructor() {
    var todos: List<TodoModel> = emptyList()
}