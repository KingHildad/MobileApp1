package com.example.mobileapp.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileapp.R
import com.example.mobileapp.navigation.ROUTE_ADD_STUDENT
import com.example.mobileapp.navigation.ROUTE_VIEW_STUDENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController){
    Box {
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "Dashboard background" ,
            contentScale = ContentScale.FillBounds)
    }

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        TopAppBar(
            title = { Text(text = "Xi Blogs", modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)) },

            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                }

                IconButton(onClick = { /*TODO*/
                    navController.navigate( ROUTE_ADD_STUDENT) }) {
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add icon")
                }

                IconButton(onClick = { /*TODO*/
                    navController.navigate( ROUTE_VIEW_STUDENT)}) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home icon")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White

            )
        )
        Spacer(modifier = Modifier.height(10.dp))


    }

}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DashBoardScreenPreview(){
    DashboardScreen(rememberNavController())
}


