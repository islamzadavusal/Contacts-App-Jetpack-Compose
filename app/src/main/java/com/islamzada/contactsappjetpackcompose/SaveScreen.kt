package com.islamzada.contactsappjetpackcompose

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.islamzada.contactsappjetpackcompose.viewmodel.SaveScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveScreen() {

    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonNumber = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    val viewModel:SaveScreenViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Save Contact") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        3.dp
                    )
                )
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = tfPersonName.value,
                    onValueChange = {tfPersonName.value = it}, label = { Text(text = "Person name")})

                TextField(
                    value = tfPersonNumber.value,
                    onValueChange = {tfPersonNumber.value = it}, label = { Text(text = "Person number")})
                
                Button(onClick = {
                    val person_name = tfPersonName.value
                    val person_number = tfPersonNumber.value
                    viewModel.save(person_name, person_number)

                    localFocusManager.clearFocus()

                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Save")
                    
                }

            }
        }

    )

}