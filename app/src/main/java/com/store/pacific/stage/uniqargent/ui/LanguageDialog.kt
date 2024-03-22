package com.store.pacific.stage.uniqargent.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LanguageDialog(modifier: Modifier,
                   onLanguageSelected:(Int)->Unit,
                   onDismissRequest: () -> Unit){

    Dialog(onDismissRequest = {onDismissRequest()}){
        Card(shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffe7f5f1)
            ),
            modifier = Modifier.wrapContentSize(align = Alignment.Center)
        ){
            val options = mapOf(1 to "English",2 to "Francais")
            var selectedOption by remember { mutableStateOf(options[0])}

            Column{
                options.forEach{
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                    ) {
                        Text(text = it.value, style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 8.dp).weight(2f))
                        Spacer(Modifier.width(40.dp))
                        RadioButton(selected = (it.value== selectedOption),
                            onClick = {selectedOption =it.value
                                onLanguageSelected(it.key)
                                onDismissRequest()
                                      },
                            colors = RadioButtonDefaults.colors(),)
                    }

                }
            }
        }
    }

}

@Preview
@Composable
fun showLanguageDialog(){
    LanguageDialog(modifier = Modifier, onLanguageSelected = { }, onDismissRequest = {})
}