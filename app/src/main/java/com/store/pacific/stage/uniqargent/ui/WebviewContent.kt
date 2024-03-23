package com.store.pacific.stage.uniqargent.ui

import android.webkit.WebSettings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewState
import com.google.accompanist.web.rememberWebViewStateWithHTMLData
import com.store.pacific.stage.R
import com.store.pacific.stage.ui.theme.ButtonFocused


@Composable
fun CommonWebView(state: WebViewState) {
    WebView(
        state,
        modifier = Modifier.fillMaxSize(0.9f),
        captureBackPresses = false,
        client = remember { object : AccompanistWebViewClient() { } },
        chromeClient = remember { object : AccompanistWebChromeClient() { }}
    )
}

@Composable
fun WebviewPage(modifier:Modifier,onAccept:()->Unit,onRefuse:()->Unit){
    val state = rememberWebViewStateWithHTMLData(
        data = "<p>一段HTML代码</p>", "utf-8", "text/html"
    )
    val privacyAgreement = rememberWebViewState(url = "https://www.baidu.com" )
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)){
        CommonWebView(privacyAgreement)
        Row(modifier=modifier.fillMaxSize(0.9f)){
            OutlinedButton(onClick = { onRefuse() }, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
                .wrapContentWidth(Alignment.Start),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White    ),
                border = BorderStroke(1.dp,color = Color(0x007c5c))){
                Text(stringResource(R.string.refuse))
            }

            Button(onClick = { onAccept() },modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
                .wrapContentWidth(Alignment.End),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0x007c5c),
//                    contentColor = Color.White,),
            ) {
                Text(stringResource(id = R.string.accept))
            }

        }
    }
}

@Preview
@Composable
fun showWeb(){
    WebviewPage(modifier = Modifier, onAccept = {},onRefuse={})
}

