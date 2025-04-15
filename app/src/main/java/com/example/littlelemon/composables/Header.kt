package com.example.littlelemon.compsables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Profile
import com.example.littlelemon.R


@Composable
fun Header(showProfileIcon: Boolean, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 10.dp, end = 10.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(3f)
        )
        if (showProfileIcon) {
            IconButton(
                onClick = { navController.navigate(Profile.route) },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header(true, rememberNavController())
}