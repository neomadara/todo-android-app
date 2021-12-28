package com.example.todoapp.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Toast(message: String) {
    val context = LocalContext.current
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    /*Column(
        content = {
            Toast.makeText(
                context,
                "Showing toast....",
                Toast.LENGTH_SHORT
            ).show()
        }, modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )*/
}

@Preview
@Composable
fun ToastPreview() {
    Toast("updating todos...")
}