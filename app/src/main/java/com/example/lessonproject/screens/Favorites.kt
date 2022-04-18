package com.example.lessonproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lessonproject.viewmodel.FavoritesViewModel
import com.example.testapp.models.Movie

@Composable
fun Favorites(
    navController: NavController = rememberNavController(),
    FavoritesViewModel: FavoritesViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Green) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Text(text = "Favorites")
                }

            }
        }
    ) {
        DisplayFavorites(navController = navController, FavoritesViewModel.favoriteMovies)
    }
}

@Composable
fun DisplayFavorites(navController: NavController, favoriteMovies: List<Movie>) {
    LazyColumn() {
        items(favoriteMovies) { movie ->
            MovieRow(movie = movie,
                onItemClick = { movieId -> navController.navigate(MovieScreens.DetailScreen.name + "/$movieId") }
            )
        }
    }
}

