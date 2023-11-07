package com.islamzada.contactsappjetpackcompose

import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.islamzada.contactsappjetpackcompose.entity.Contacts
import com.islamzada.contactsappjetpackcompose.viewmodel.DetailsScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(getperson: Contacts) {

    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonNumber = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    val viewModel: DetailsScreenViewModel = viewModel()

    LaunchedEffect(key1 = true){
        tfPersonName.value = getperson.person_name
        tfPersonNumber.value = getperson.person_number
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Contact details") },
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
                    onValueChange = {tfPersonName.value = it}, label = { Text(text = "Person name") })

                TextField(
                    value = tfPersonNumber.value,
                    onValueChange = {tfPersonNumber.value = it}, label = { Text(text = "Person number") })

                Button(onClick = {
                    val person_name = tfPersonName.value
                    val person_number = tfPersonNumber.value
                    viewModel.update(getperson.person_id, person_name, person_number)


                    localFocusManager.clearFocus()

                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Update")

                }

            }
        }

    )

}