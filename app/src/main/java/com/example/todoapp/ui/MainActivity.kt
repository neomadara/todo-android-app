package com.example.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.view.TodoMainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            MaterialTheme {
                TodoMainScreen(
                    viewModel = hiltViewModel()
                )
            }
        }
    }
}