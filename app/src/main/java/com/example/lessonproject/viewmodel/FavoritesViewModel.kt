package com.example.lessonproject.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.testapp.models.Movie

class FavoritesViewModel : ViewModel() {

    var favoriteMovies = mutableStateListOf<Movie>()

    fun addToFavorites(movie: Movie) {
        if (movie in favoriteMovies) {
            favoriteMovies.remove(movie)
        } else {
            favoriteMovies.add(movie)
        }
    }

    fun getFavorites(): List<Movie> {
        return favoriteMovies
    }

    private fun exists(movie: Movie) : Boolean {
        return favoriteMovies.any {m -> m.id == movie.id}
    }

    fun isFavorite(movie: Movie) : Boolean {
        return exists(movie)
    }
}