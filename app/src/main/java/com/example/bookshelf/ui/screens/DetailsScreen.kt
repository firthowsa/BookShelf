package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.model.Book

@Composable
fun DetailsScreen(
    modifier: Modifier=Modifier,
    viewModel: DetailsViewModel,
){
    val uiStateDetail=viewModel.uiStateDetail.collectAsState().value
    
    when(uiStateDetail){
        is DetailsUiState.Loading -> {
            LoadingScreen()
        }
        is DetailsUiState.Error -> {
            ErrorScreen()
        }
        is DetailsUiState.Success -> {
            BookDetails(book = uiStateDetail.bookItem)
        }
    }

}

@Composable
fun BookDetails(book: Book,modifier: Modifier=Modifier){
   // Card(elevation = 8.dp, modifier = Modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
           item{
               book.volumeInfo.description?.let { Text(text = it) }
           }
            
        }
        
  //  }
    
}