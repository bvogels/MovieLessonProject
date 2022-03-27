package com.example.lessonproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Composable
fun DisplayFavorites(
    navController: NavController = rememberNavController()
) {
    var fav : Movie = getMovies()[0]
    val favId = listOf("tt0944947", "tt0903747", "tt0848228")
    val x: ListIterator<String> = favId.listIterator()
    while (x.hasNext()) {
        fav = favoriteMovie(x.next())
    }
    //favoriteMovie(movieID = { for (e in favId) })
    //val fav = favoriteMovie(movieID = favId[0])


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
        Content(movie = fav)

    }
}

fun favoriteMovie(movieID: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieID }[0]
}

@Composable
fun Content(movie: Movie) {
    Column {
        MovieRow(movie = movie)
    }
}