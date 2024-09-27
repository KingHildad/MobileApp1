package com.example.mobileapp.ui.theme.screens.login




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileapp.R
import com.example.mobileapp.data.AuthViewModel
import com.example.mobileapp.navigation.ROUTE_VIEW_STUDENT


@Composable
fun LoginScreen(navController: NavController){
    val context= LocalContext.current
    var email by remember {
        mutableStateOf(value = "")
    }
    var password by remember {
        mutableStateOf(value = "")
    }
    Box {


        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = R.drawable.pic2),
            contentDescription = "Dashboard background" ,
            contentScale = ContentScale.FillBounds)
    }
    Row(modifier = Modifier.fillMaxWidth()){Text(text = "LOGIN HERE",
        fontSize = 20.sp,
        color = Color.Cyan,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(20.dp)
    )}
    Spacer(modifier = Modifier.height(10.dp))

    Spacer(modifier = Modifier.height(10.dp))

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ){



        OutlinedTextField(value = email,
            onValueChange = {
                    newEmail->email=newEmail
            },
            label = { Text(text = "Enter Email",color = Color.Cyan)},
            placeholder = { Text(text = "Please enter email",color = Color.Cyan)},
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = password,
            onValueChange = {newPassword->password = newPassword},
            label = { Text(text = "Enter Password",color = Color.Cyan)},
            placeholder = { Text(text = "Please enter password",color = Color.Cyan)},
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            val mylogin = AuthViewModel(navController, context)
            mylogin.login(email.trim(),password.trim())
            navController.navigate(ROUTE_VIEW_STUDENT)
        },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.Cyan)) {
            Text(text = "CLICK to LOGIN",
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp))
        }
    }




}








@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}
