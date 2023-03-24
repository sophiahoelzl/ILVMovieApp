package com.example.ilvmovieapp.screens

sealed class Screens(val route: String){
    object Home : Screens("HomeScreen")
    object Favorite : Screens(route = "FavoritesScreen")
    object Detail : Screens("DetailScreen/{movieID}"){
        fun passID(id: String): String{
            return "DetailScreen/$id"
        }
    }
}