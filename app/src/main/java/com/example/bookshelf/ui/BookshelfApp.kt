package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.ui.screens.BookshelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import com.example.bookshelf.R
import com.example.bookshelf.ui.navigation.AppDestinations
import com.example.bookshelf.ui.navigation.BookshelfNavHost
import com.example.bookshelf.ui.screens.components.MyTopAppBar


@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    // Notes: Set Nav Controller
    val navController = rememberNavController()
    // Notes: Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Notes: Get the name of the current screen check for null
    val currentScreen = AppDestinations.valueOf(
        backStackEntry?.destination?.route ?: AppDestinations.HomeScreen.name
    )

    // Notes: Boolean to check if we can nagigate back. Check stack
    val canNavigateBack = navController.previousBackStackEntry != null


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                onNavigateUpClicked = { navController.navigateUp() }
            )
        }
    ) {

    Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
            //HomeScreen(bookshelfUiState = bookshelfViewModel.bookshelfUiState)
            BookshelfNavHost(navController = navController)
        }
    }
}