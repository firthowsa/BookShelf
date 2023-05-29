package com.example.bookshelf.ui.screens

import android.text.Spannable.Factory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.repository.BookshelfRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookshelfViewModel(private val bookshelfRepository: BookshelfRepository):ViewModel() {
    var bookshelfUiState:BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    var selectedBookId by mutableStateOf("")


    init {
        getBooks()
    }

    private  fun getBooks(){
        viewModelScope.launch {
           bookshelfUiState= BookshelfUiState.Loading
            bookshelfUiState=try {
               val books= bookshelfRepository.getBooks()
                if(books ==  null){
                    BookshelfUiState.Error
                }else if(books.isEmpty()){
                    BookshelfUiState.Success(emptyList())
                }else{
                    BookshelfUiState.Success(books)
                }

            }catch (e: IOException){
                BookshelfUiState.Error
            }catch (e: HttpException){
                BookshelfUiState.Error
            }
        }

    }
    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as BookshelfApplication)
                val  bookshelfRepository=application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}