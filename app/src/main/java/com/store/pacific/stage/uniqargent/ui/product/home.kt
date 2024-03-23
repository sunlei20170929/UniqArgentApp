package com.store.pacific.stage.uniqargent.ui.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.store.pacific.stage.MainViewModel

@Composable
fun HomePage(modifier: Modifier,viewModel:MainViewModel){

    val sms by viewModel.getSms("13391860059").c

}


