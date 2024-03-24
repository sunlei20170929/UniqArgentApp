package com.store.pacific.stage.uniqargent.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.store.pacific.stage.MainViewModel

@Composable
fun HomePage(modifier: Modifier,viewModel:MainViewModel){

    val testSms by remember {viewModel.sms}

    Column {
        Text(text=testSms)
        Button(onClick = {viewModel.getSms("ttt")}){
            Text("GET SMS")
        }
    }


}



