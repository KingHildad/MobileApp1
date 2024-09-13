package com.example.mobileapp.ui.theme.screens.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileapp.navigation.ROUTE_DASHBOARD
import com.example.mobileapp.navigation.ROUTE_LOGIN
import com.example.mobileapp.navigation.ROUTE_REGISTER
import com.example.mobileapp.ui.theme.screens.login.LoginScreen

@Composable
fun StartScreen(navController: NavController){
    Column (modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center){
        Button(onClick = { navController.navigate(ROUTE_REGISTER) }, modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text(text = "REGISTER",textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.width(25.dp))
        Button(onClick = { navController.navigate(ROUTE_LOGIN) }, modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text(text = "LOGIN",textAlign = TextAlign.Center)
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview(){
    StartScreen(rememberNavController())
}