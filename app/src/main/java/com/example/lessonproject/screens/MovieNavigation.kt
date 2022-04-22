package com.example.lessonproject.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lessonproject.viewmodel.FavoritesViewModel

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val favoritesViewModel : FavoritesViewModel = viewModel()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController, favoritesViewModel)}
        composable(MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController, movieID = backStackEntry.arguments?.getString("movie"), favoritesViewModel
            )}
        composable(MovieScreens.FavoritesScreen.name) { Favorites(navController = navController, favoritesViewModel) }

    }
}