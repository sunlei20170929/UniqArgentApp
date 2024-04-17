package com.store.pacific.stage.uniqargent.ui.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.store.pacific.stage.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomePage(modifier: Modifier,viewModel:MainViewModel){

    val testSms by remember {viewModel.sms}

//    Column {
//        Text(text=testSms)
//        Button(onClick = {viewModel.getSms("182111111")}){
//            Text("GET SMS")
//        }
//    }

    val items = remember {
        (1..100).map{" items $it"}
    }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()){
        PullToRefreshLazyColumn(data = items, content = {itemTitle->
            Text(text = itemTitle,modifier = Modifier.padding(16.dp))
                                                         },
            isRefreshing = isRefreshing, onRefresh = {
                scope.launch {
                    isRefreshing = true
                    delay(3_000)
                    isRefreshing = false
                }
            })

        Button(onClick = {isRefreshing = true}){
            Text(text = "Refresh")
        }
    }


}



