package com.example.bookshelf.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookshelf.ui.screens.BookshelfViewModel
import com.example.bookshelf.ui.screens.DetailsScreen
import com.example.bookshelf.ui.screens.DetailsViewModel
import com.example.bookshelf.ui.screens.HomeScreen

@Composable
fun BookshelfNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    val detailViewModel: DetailsViewModel = viewModel(factory = DetailsViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HomeScreen.name,
        modifier= modifier ){

        composable(route = AppDestinations.HomeScreen.name){
            HomeScreen(
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                onDetailsClick = {
                   bookshelfViewModel.selectedBookId=it.id
                    navController.navigate(AppDestinations.DetailsScreen.name)
                }
            )
        }

        composable(route =AppDestinations.DetailsScreen.name){
            detailViewModel.getBook(bookshelfViewModel.selectedBookId)

            DetailsScreen(viewModel =detailViewModel )
        }

    }

}