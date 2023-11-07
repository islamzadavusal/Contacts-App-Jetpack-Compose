package com.islamzada.contactsappjetpackcompose

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.islamzada.contactsappjetpackcompose.entity.Contacts
import com.islamzada.contactsappjetpackcompose.ui.theme.ContactsAppJetpackComposeTheme
import com.islamzada.contactsappjetpackcompose.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsAppJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen"){
        composable("main_screen"){
            MainScreen(navController = navController)
        }
        composable("save_screen"){
            SaveScreen()
        }
        composable("details_screen/{person}", arguments = listOf(
            navArgument("person"){ type = NavType.StringType }
        )){
            val json = it.arguments?.getString("person")
            val gson = Gson().fromJson(json, Contacts::class.java)
            DetailsScreen(gson)
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val searchTest = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }

    val viewModel: MainScreenViewModel = viewModel()
    val contactsList = viewModel.contactsList.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModel.loadContacts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (searchTest.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                viewModel.search(it)
                            },
                            label = { Text(text = "Search") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,//Arkaplan rengi
                                focusedIndicatorColor = Color.Gray,//Seçildiğinde Belirteç rengi
                                unfocusedIndicatorColor = Color.Gray,//Seçilmediğinde Belirteç rengi
                                textColor = Color.Black//Yazı rengi
                            )
                        )
                    } else {
                        Text(text = "Contacts")
                    }
                },
                actions = {
                    if (searchTest.value) {
                        IconButton(onClick = {
                            searchTest.value = false
                            tf.value = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.close_icon),
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            searchTest.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                    }
                },
                // background TopAppBar
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            )
        },
        content = {
            LazyColumn(
                // Add padding to separate the content from the TopAppBar
                contentPadding = PaddingValues(top = 70.dp)
            ) {
                items(
                    count = contactsList.value!!.count(),
                    itemContent = {
                        val person = contactsList.value!![it]
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.clickable {
                                    val personJson = Gson().toJson(person)
                                    navController.navigate("details_screen/${personJson}")
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "${person.person_name}: ${person.person_number}")
                                    IconButton(onClick = {
                                        viewModel.delete(person.person_id)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "",
                                            tint = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("save_screen") },
                containerColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )
        }
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactsAppJetpackComposeTheme {
    }
}