package com.example.mobileapp.ui.theme.screens.student

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import coil.compose.rememberImagePainter
import com.example.mobileapp.R
import com.example.mobileapp.data.StudentViewModel
import com.example.mobileapp.navigation.ROUTE_VIEW_STUDENT

@Composable
fun Student(navController: NavController){
    val imageUri = rememberSaveable(){
        mutableStateOf<Uri?>(null)
    }
    val painter = rememberImagePainter(
        data = imageUri.value ?: R.drawable.ic_person,
        builder = {
            crossfade(true)
        }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
            uri: Uri? ->
        uri?.let { imageUri.value = it }
    }
    var firstname by remember {
        mutableStateOf(value = "")
    }

    /*var lastname by remember {
        mutableStateOf(value = "")
    }
    var gender by remember {
        mutableStateOf(value = "")
    }*/
    var desc by remember {
        mutableStateOf(value = "")
    }

    Box {


        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = R.drawable.pic2),
            contentDescription = "Dashboard background" ,
            contentScale = ContentScale.FillBounds)
    }
    Row(modifier = Modifier.fillMaxWidth()){Text(text = "ADD YOUR BLOG",
        fontSize = 20.sp,
        color = Color.Cyan,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(20.dp)
    )}


    Column (modifier = Modifier.fillMaxWidth()
    ){

        Spacer(modifier = Modifier.height(80.dp))

        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card (
                shape = RectangleShape,
                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp)
            ){
                Image(painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable { launcher.launch("image/*") },
                    contentScale = ContentScale.Crop)
            }
            Text(text = "Attach Picture Here",color = Color.Cyan)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Blog Name",modifier = Modifier.align(Alignment.CenterHorizontally),fontSize = 23.sp)
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = firstname,
            onValueChange ={newFirstName -> firstname = newFirstName},
            label = { Text(text = "Enter Your Blog Name",color = Color.Cyan) },
            placeholder = { Text(text = "Please Enter Blog Name",color = Color.Cyan) },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Description",modifier = Modifier.align(Alignment.CenterHorizontally),fontSize = 23.sp)
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = desc,
            onValueChange ={newDesc->desc=newDesc},
            label = { Text(text = "Description",color = Color.Cyan) },
            placeholder = { Text(text = "Descption",color = Color.Cyan) },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .height(150.dp),
            singleLine = false)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(onClick = {
                navController.navigate(ROUTE_VIEW_STUDENT)
            }) {
                Text(text = "BACK TO BLOGS")
            }
            val context= LocalContext.current

            Button(onClick = {
                val studentRepository=StudentViewModel(navController, context)
                if (imageUri.value!=null){
                    studentRepository.saveStudent(
                        filePath = imageUri.value!!,
                        firstname=firstname,
                        desc=desc
                    )
                    navController.navigate(ROUTE_VIEW_STUDENT)
                }else{
                    Toast.makeText(context,"Please select an image",Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = "PUBLISH BLOG")
            }

        }







    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Student(rememberNavController())
}
