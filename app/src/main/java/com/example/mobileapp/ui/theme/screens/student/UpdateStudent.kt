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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import coil.compose.rememberImagePainter
import com.example.mobileapp.R
import com.example.mobileapp.data.StudentViewModel
import com.example.mobileapp.models.Student
import com.example.mobileapp.navigation.ROUTE_LOGOUT
import com.example.mobileapp.navigation.ROUTE_UPDATE_STUDENT
import com.example.mobileapp.navigation.ROUTE_VIEW_STUDENT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStudent(navController: NavController, id: String) {
    var imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    var existingImageUrl by rememberSaveable { mutableStateOf("") }

    val painter = rememberImagePainter(data = imageUri.value ?: R.drawable.ic_person,
        builder = { crossfade(true) })

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent())
    { uri: Uri? -> imageUri.value = uri }

    var firstname by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    val context = LocalContext.current

    val currentDataRef = FirebaseDatabase.getInstance().getReference()
        .child("Students/$id")




    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val student = snapshot.getValue(Student::class.java)
                student?.let {
                    firstname = it.firstname
                    desc = it.desc
                    imageUri.value = it.imageUrl?.let { uri -> Uri.parse(uri) }
                    existingImageUrl = it.imageUrl ?: ""
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }

    Box {
        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = R.drawable.pic2),
            contentDescription = "Dashboard background" ,
            contentScale = ContentScale.FillBounds)
    }

    Scaffold{ innerPadding ->

        Box {
            Spacer(modifier = Modifier.height(10.dp))

            Image(painter = painterResource(id = R.drawable.pic2),
                contentDescription = "Dashboard background" ,
                contentScale = ContentScale.FillBounds)
        }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
                .fillMaxWidth()

        ) {
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
                            navController.navigate( ROUTE_VIEW_STUDENT)}) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home icon")
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White

                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
            }

            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()){Text(text = "UPDATE YOUR BLOG",
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

                Spacer(modifier = Modifier.height(30.dp))







                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(180.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(180.dp)
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop
                    )
                }
                Text(text = "Change Picture Here")
                Spacer(modifier = Modifier.height(10.dp))

            }
            OutlinedTextField(
                modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter Blog Name") },
                placeholder = { Text(text = "Please Enter Blog Name") },
                value = firstname,
                onValueChange = { newName -> firstname = newName }
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                modifier = Modifier
                    .height(160.dp)
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                label = { Text(text = "Enter description") },
                placeholder = { Text(text = "Please Enter brief description") },
                value = desc,
                singleLine = false,
                onValueChange = { newDesc -> desc = newDesc }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    val studentRepository = StudentViewModel(navController, context)
                    navController.navigate( ROUTE_VIEW_STUDENT)

                    studentRepository.updateStudent(
                        context = context,
                        navController = navController,
                        filePath = imageUri.value ?: Uri.EMPTY,
                        firstname = firstname,
                        desc = desc,
                        id = id,
                        currentImageUrl = existingImageUrl // Pass the current image URL
                    )
                }) {
                    Text(text = "Update Blog")
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpdateStudentPreview() {
    UpdateStudent(rememberNavController(), id = "")
}

