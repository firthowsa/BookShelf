package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    onDetailsClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier)
        is BookshelfUiState.Success -> PhotosGridScreen( bookshelfUiState.books,onDetailsClick =onDetailsClick, modifier)
        is BookshelfUiState.Error -> ErrorScreen(modifier)
    }
}
@Composable
fun PhotosGridScreen(books: List<Book>,onDetailsClick: (Book) -> Unit, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        //
        items(
            items = books,
            key = {book -> book.id}

        ){book ->
          BookshelfCard(book = book,onDetailsClick= onDetailsClick)

        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookshelfCard(book: Book,onDetailsClick: (Book) -> Unit,modifier: Modifier=Modifier){
    Card(elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp),
        onClick = {onDetailsClick(book)}
            //.fillMaxWidth()

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks?.httpsThumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.books),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth()
            )



        }

    }

}
/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(com.example.bookshelf.R.drawable.loading_img),
            contentDescription = stringResource(com.example.bookshelf.R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}