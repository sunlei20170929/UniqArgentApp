package com.store.pacific.stage.uniqargent.ui

import android.webkit.WebSettings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewStateWithHTMLData


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
fun WebviewPage(modifier:Modifier){
    val state = rememberWebViewStateWithHTMLData(
        data = "<p>一段HTML代码</p>", "utf-8", "text/html"
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().padding(top=8.dp)){
        CommonWebView(state)
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            OutlinedButton(onClick = {  }) {
                Text("Refuse")
            }

            Button(onClick = {  }) {
                Text("Accept")
            }

        }
    }
}

@Preview
@Composable
fun showWeb(){
    WebviewPage(modifier = Modifier)
}

