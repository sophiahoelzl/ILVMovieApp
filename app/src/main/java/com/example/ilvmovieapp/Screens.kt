package com.example.ilvmovieapp

sealed class Screens(val route: String){
    object Home : Screens("HomeScreen")
    object Detail : Screens("DetailScreen/{movieID}"){
        fun passID(id: String): String{
            return "DetailScreen/$id"
        }
    }
}