package com.example.lessonproject.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lessonproject.viewmodel.FavoritesViewModel
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    favoritesViewModel: FavoritesViewModel = viewModel()
) {

    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")

                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(MovieScreens.FavoritesScreen.name) }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorites"
                            )
                            Text(
                                modifier = Modifier.clickable(onClick = {
                                    navController.navigate(MovieScreens.FavoritesScreen.name)
                                }),
                                text = "Favorites", fontSize = 13.sp
                            )
                        }
                    }
                }
            )
        }
    ) {
        MainContent(
            getMovies(),
            navController = navController,
            favoritesViewModel
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(movies : List<Movie>,
                navController: NavController,
                favoritesViewModel: FavoritesViewModel = viewModel()) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie,
            onItemClick = { movieId ->
                navController.navigate(MovieScreens.DetailScreen.name + "/$movieId")},
            ){
                FavoriteMovie(movie, favoritesViewModel.isFavorite(movie)){ movie ->
                    favoritesViewModel.addToFavorites(movie)
                }
            }
        }
    }
}

