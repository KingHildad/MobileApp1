package com.example.mobileapp.ui.theme.screens.logout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileapp.R
import com.example.mobileapp.navigation.ROUTE_START
import com.example.mobileapp.navigation.ROUTE_VIEW_STUDENT

@Composable
fun LogoutScreen(navController: NavController){
    Box {
        Image(painter = painterResource(id = R.drawable.pic2),
            contentDescription = "Dashboard background" ,
            contentScale = ContentScale.FillBounds)
    }
    Column (modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center){
        Button(onClick = { navController.navigate(ROUTE_START) }, modifier = Modifier.align(
            Alignment.CenterHorizontally))
        {
            Text(text = "Logout",textAlign = TextAlign.Center)
        }
        Button(onClick = { navController.navigate(ROUTE_VIEW_STUDENT) }, modifier = Modifier.align(
            Alignment.CenterHorizontally))
        {
            Text(text = "Back to Blogs",textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogoutScreenPreview(){
    LogoutScreen(rememberNavController())
}

