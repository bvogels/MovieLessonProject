package com.example.lessonproject.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {}
) {
    var more by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(movie.id) },
        shape = RectangleShape,
        elevation = 6.dp
    )
    {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(130.dp)
                    .padding(12.dp),
                elevation = 6.dp
            ) {
                Image(painter = rememberImagePainter(
                    data = movie.images[0],
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                    contentDescription = "Movie poster"
                )
            }
            Column() {
                Text(text = movie.title, fontWeight = FontWeight.Bold)
                Text(text = movie.director)
                Text(text = movie.year)
                if (!more) {
                    Icon(
                        modifier = Modifier.clickable(onClick = { more = !more }),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show more"
                    )
                } else {
                    Icon(
                        modifier = Modifier.clickable(onClick = { more = false }),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Show less"
                    )
                    AnimatedVisibility(visible = more) {
                        Column() {
                            Text(text = movie.plot)
                            Text(text = movie.actors)
                            Text(text = movie.genre)
                            Text(text = movie.rating)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalMovieImageGallery(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier.padding(12.dp).size(240.dp),
                elevation = 4.dp
            ) {
                Image(painter = rememberImagePainter(data = image),
                    contentDescription = "Movie image")
            }
        }
    }
}