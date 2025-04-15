package com.example.littlelemon.composables

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import button
import com.example.littlelemon.Home
import com.example.littlelemon.R
import com.example.littlelemon.RegistrationUtil
import com.example.littlelemon.User
import com.example.littlelemon.compsables.Header
import com.example.littlelemon.ui.theme.Shapes
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.stroke
import com.example.littlelemon.ui.theme.yellow
import label
import title
import welcome


@Composable
fun Onboarding(navController: NavHostController) {

    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column {
        Header(false, navController)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(green),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.welcome), style = welcome
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start

        ) {
            Text(
                text = stringResource(R.string.personalInformation),
                style = title,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.firstName),
                    style = label,
                    modifier = Modifier.padding(5.dp)

                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.lastName),
                    style = label,
                    modifier = Modifier.padding(5.dp)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.email),
                    style = label,
                    modifier = Modifier.padding(5.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = Shapes.medium,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = charcoal,
                        containerColor = yellow
                    ),
                    border = BorderStroke(1.dp, stroke),
                    onClick = {
                        try {
                            RegistrationUtil.registerUser(context, User(firstName, lastName, email))
                            Toast.makeText(
                                context,
                                R.string.registrationSuccessful,
                                Toast.LENGTH_LONG
                            ).show()
                            navController.navigate(Home.route)
                        } catch (e: IllegalArgumentException) {
                            Toast.makeText(
                                context,
                                R.string.registrationUnsuccessful,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        style = button,
                        text = stringResource(R.string.register),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(rememberNavController())
}