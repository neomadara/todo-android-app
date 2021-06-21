package com.example.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.model.TodoModel
import com.example.todoapp.databinding.AdapterTodoBinding
class TodoRecyclerAdapter: RecyclerView.Adapter<TodoViewHolder>() {
    var todoList = mutableListOf<TodoModel>()

    fun setTodo(todos: List<TodoModel>) {
        this.todoList = todos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterTodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.binding.todoId.text = todo.id.toString()
        holder.binding.todoTitle.text = todo.title
        holder.binding.todoCompleted.text = todo.completed
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}

class TodoViewHolder(val binding: AdapterTodoBinding) : RecyclerView.ViewHolder(binding.root) {
}