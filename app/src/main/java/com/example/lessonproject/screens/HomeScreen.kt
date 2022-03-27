package com.example.lessonproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

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
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorites"
                            )
                            Text(
                                modifier = Modifier.clickable(onClick = {
                                    navController.navigate(
                                        MovieScreens.FavoritesScreen.name
                                    )
                                }),
                                text = "Favorites", fontSize = 13.sp
                            )
                        }
                    }
                }
            )
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(movies: List<Movie> = getMovies(), navController: NavHostController) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate(MovieScreens.DetailScreen.name + "/$movieId")
            }
        }
    }
}
