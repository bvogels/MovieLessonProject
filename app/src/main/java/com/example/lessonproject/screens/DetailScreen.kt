package com.example.lessonproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieID: String? = "tt0499549"
) {

    val movie = filterMovie(movieID = movieID)

    Scaffold (
        topBar = {
            TopAppBar(backgroundColor = Color.Red) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Text(text = movie.title)
                }

            }
        }
    ) {
        MainContent(movie = movie)

    }
}

fun filterMovie(movieID: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieID }[0]
}

@Composable
fun MainContent(movie: Movie) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column {
            MovieRow(movie = movie)
            Spacer(modifier = Modifier.width(20.dp))
            Divider()
            HorizontalMovieImageGallery(movie = movie)
        }
    }
}