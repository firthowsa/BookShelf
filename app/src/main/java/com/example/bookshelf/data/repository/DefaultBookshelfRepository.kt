package com.example.bookshelf.data.repository

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService
/**
 * Default Implementation of repository that retrieves volumes data from underlying data source.
 */
class DefaultBookshelfRepository(private val bookshelfApiService: BookshelfApiService) : BookshelfRepository{
//    override suspend fun getBooks(query: String): List<Book>? {
        override suspend fun getBooks(): List<Book>? {

            return try {
          ///  val response=bookshelfApiService.getBooks(query)
             val response=bookshelfApiService.getBooks()

            if (response.isSuccessful){
                response.body()?.items ?: emptyList()
            }else{
                emptyList()

            }


        }catch (e: java.lang.Exception){
            e.printStackTrace()
            null
        }

    }

    override suspend fun getBook(id: String): Book? {
        return try{
            val response=bookshelfApiService.getBook(id)
            if(response.isSuccessful){
                response.body()
            }else{
                null
            }
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }
}