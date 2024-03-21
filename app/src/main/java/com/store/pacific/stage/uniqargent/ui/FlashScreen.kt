package com.store.pacific.stage.uniqargent.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.store.pacific.stage.R


@Composable
fun FlashScreen(modifier: Modifier){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
            Spacer(Modifier.height(40.dp))
            Text(stringResource(id = R.string.app_name))
        }

    }
}

@Preview
@Composable
fun showFlashScreen(){
    FlashScreen(modifier = Modifier)
}