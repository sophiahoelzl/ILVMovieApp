package com.example.ilvmovieapp

sealed class Screens(val route: String){
    object Home : Screens("HomeScreen")
    object Detail : Screens("DetailScreen")
}
//hiefw