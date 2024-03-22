package com.store.pacific.stage.uniqargent.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.store.pacific.stage.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SplashWaitTime: Long = 5_000
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FlashScreen(modifier: Modifier,onTimeout: () -> Unit){

    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(Unit) {
        delay(SplashWaitTime)
        currentOnTimeout()
    }
//    val scope = rememberCoroutineScope()
//    scope.launch {
//        delay(SplashWaitTime)
//        currentOnTimeout()
//        onTimeout()
//    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(painterResource(id = R.drawable.ic_crane_drawer), contentDescription = null,modifier
                .wrapContentSize())
            Spacer(Modifier.height(40.dp))
            Text(stringResource(id = R.string.app_name))
        }

    }
}

@Preview
@Composable
fun showFlashScreen(){
    FlashScreen(modifier = Modifier, onTimeout = {})
}