package com.example.littlelemon.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.compsables.Header


@Composable
fun Home(navController: NavController){

    Column {
        Header(true, navController)
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(rememberNavController())
}