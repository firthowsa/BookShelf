package com.example.bookshelf.data.di

import com.example.bookshelf.data.repository.BookshelfRepository
import com.example.bookshelf.network.BookshelfApiService

interface AppContainer {
   val bookshelfRepository: BookshelfRepository

}