package com.example.todoapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://serverless-neomadara.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}