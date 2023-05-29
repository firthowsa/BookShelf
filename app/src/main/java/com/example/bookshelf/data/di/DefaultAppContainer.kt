package com.example.bookshelf.data.di

import com.example.bookshelf.data.repository.BookshelfRepository
import com.example.bookshelf.data.repository.DefaultBookshelfRepository
import com.example.bookshelf.network.BookshelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefaultAppContainer : AppContainer{
    private val BASE_URL="https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

        //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

   private val retrofitService:BookshelfApiService by lazy {
       retrofit.create(BookshelfApiService::class.java)
   }

    //dependency injection
    override val bookshelfRepository: BookshelfRepository by lazy {
        DefaultBookshelfRepository(retrofitService)
    }

}