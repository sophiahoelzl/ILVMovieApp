package com.example.ilvmovieapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopAppBar(navController: NavController, title: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF0D5088))) {
        Column (modifier = Modifier
            .size(50.dp, 60.dp)
            , verticalArrangement = Arrangement.Center){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
                .clickable {
                    navController.popBackStack()
                })
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .size(100.dp), verticalArrangement = Arrangement.Center){
            Text(text = title, fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}