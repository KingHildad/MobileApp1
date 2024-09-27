package com.example.mobileapp.ui.theme.screens.student





import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.mobileapp.R
import com.example.mobileapp.data.StudentViewModel
import com.example.mobileapp.models.Student
import com.example.mobileapp.navigation.ROUTE_ADD_STUDENT
import com.example.mobileapp.navigation.ROUTE_LOGOUT
import com.example.mobileapp.navigation.ROUTE_UPDATE_STUDENT
import com.example.mobileapp.navigation.ROUTE_VIEW_STUDENT
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewStudentsScreen(navController: NavHostController){
    val auth: FirebaseAuth = Firebase.auth
    Box {
        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = R.drawable.pic2),
            contentDescription = "Dashboard background" ,
            contentScale = ContentScale.FillBounds)
    }

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        val context = LocalContext.current
        val studentRepository = StudentViewModel(navController,context)


        val emptyUploadState = remember { mutableStateOf(Student("","","","")) }
        val emptyUploadsListState = remember { mutableStateListOf<Student>() }

        val students = studentRepository.viewStudent(emptyUploadState, emptyUploadsListState)

        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            TopAppBar(
                title = { Text(text = "Feedback Blogs", modifier = Modifier.padding(10.dp).align(Alignment.CenterVertically)) },

                actions = {
                    IconButton(onClick = { /*TODO*/
                        navController.navigate( ROUTE_LOGOUT) }) {
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


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "All Blogs",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn{
                items(students){
                    StudentItem(
                        imageUrl = it.imageUrl,
                        firstname = it.firstname,
                        desc = it.desc,
                        id = it.id,
                        navController = navController,
                        studentRepository = studentRepository
                    )
                }
            }
        }

    }

}

@Composable
fun StudentItem(imageUrl:String,firstname:String,desc:String,id:String,navController: NavHostController,studentRepository:StudentViewModel){
    var showFullText by remember { mutableStateOf(false) }
    val context= LocalContext.current

    Column (modifier = Modifier.fillMaxWidth()){
        Card(modifier = Modifier
            .padding(10.dp)
            .height(370.dp)
            .fillMaxWidth()
            .animateContentSize(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = Color.Gray
            )
        )

        {
            Column() {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Image(modifier = Modifier
                        .width(400.dp)
                        .height(250.dp)
                      .padding(10.dp),
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop)




                    }
                }


                Column(modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 15.dp)
                    .verticalScroll(rememberScrollState()))
                {
                    Text(text = "BLOG NAME",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = firstname,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold)

                    Text(text = "DESCRIPTION",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(modifier = Modifier.clickable {
                        showFullText = !showFullText
                    },
                        text = desc,
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = if (showFullText) 100 else 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row {
                        Button(
                            onClick = {
                                studentRepository.deleteStudent(context ,id,navController)
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        ) {
                            Text(text = "DELETE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {
                            navController.navigate(ROUTE_UPDATE_STUDENT+"/$id")
                        },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                            Text(text = "UPDATE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                        }

                }




            }

        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewBlogPreview() {
    ViewStudentsScreen(rememberNavController())
}
