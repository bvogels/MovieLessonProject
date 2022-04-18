package com.example.lessonproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.example.lessonproject.screens.MovieNavigation
import com.example.lessonproject.viewmodel.FavoritesViewModel
import com.example.testapp.models.getMovies

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movies = getMovies()
        val vm : FavoritesViewModel by viewModels()

        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MovieNavigation()
}



