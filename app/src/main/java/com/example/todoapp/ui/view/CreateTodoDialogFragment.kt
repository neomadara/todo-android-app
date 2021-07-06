package com.example.todoapp.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.todoapp.databinding.FragmentTodoAddDialogBinding
import com.example.todoapp.ui.viewmodel.TodoViewModel

class CreateTodoDialogFragment: DialogFragment() {
    private val todoViewModel: TodoViewModel by viewModels();
    private var _binding: FragmentTodoAddDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentTodoAddDialogBinding.inflate(LayoutInflater.from(context))

        val dialog = activity?.let {
            Dialog(it)
        }

        if (dialog != null) {
            binding.btnCancel.setOnClickListener {
                dismiss()
            }

            binding.btnCreateTodo.setOnClickListener {
                todoViewModel.saveTodo(binding.etTodoTitle.text.toString())
                // TODO: deberia esperar una respuesta y luego hacer el dissmis????
                // dismiss()
            }
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}