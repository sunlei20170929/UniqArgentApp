package com.store.pacific.stage.uniqargent.ui.product

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.store.pacific.stage.R


sealed class BottomItem(
    val route: String,
    val iconSelect: Int,
    val iconUnselect: Int,
) {
    data object Home : BottomItem(
        route = "home",
        iconSelect = R.mipmap.homedark,
        iconUnselect = R.mipmap.homedark
    )

    data object Got : BottomItem(
        route = "Got",
        iconSelect = R.mipmap.gotgray,
        iconUnselect = R.mipmap.gotgray
    )

    data object Mine : BottomItem(
        route = "Mine",
        iconSelect = R.mipmap.minegray,
        iconUnselect = R.mipmap.minegray,
    )

}
val mBottomTabItems = listOf(BottomItem.Home,BottomItem.Got,BottomItem.Mine)

@Composable
fun BottomNav(modifier:Modifier){
    var bottomSelectedState by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { TopBarWidget() },
        bottomBar = { BottomBarWidget(bottomSelectedState, mBottomTabItems) {
                bottomSelectedState = it
            }
        }){
        Log.d("bottom padding", it.calculateBottomPadding().toString())
    }

}

@Composable
fun BottomBarWidget(
    selectedPosition: Int,
    bottomItems: List<BottomItem>,
    onItemSelected: (position: Int) -> Unit
) {
    BottomNavigation(backgroundColor = Color.Gray) {
        bottomItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedPosition == index,
                onClick = { onItemSelected.invoke(index) },
                icon = {
                    var iconRes = item.iconUnselect
                    var iconColor = Color.Gray
                    if (selectedPosition == index) {
                        iconRes = item.iconSelect
                        iconColor = Color.Green
                    }
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 4.dp),
                        tint = iconColor,
                    )
                },
                label = {
                    val labelStyle = if (selectedPosition == index) {
                        TextStyle(
                            fontWeight = FontWeight.Medium,

                            fontSize = 11.sp
                        )
                    } else {
                        TextStyle(
                            fontWeight = FontWeight.Normal,

                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = bottomItems[index].route,
                        style = labelStyle,
                    )
                },
            )
        }
    }
}


@Composable
fun TopBarWidget() {
    TopAppBar(title = {
        Text(
            text = "微信",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.background
            )
        )
    }, backgroundColor = Color.Green)
}

@Preview(
    showBackground = true,
    widthDp = 375,
    heightDp = 50,
    backgroundColor = 0xFFFF5959
)
@Composable
fun PreviewTopBarWidgetLight() {
    TopBarWidget()
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBarWidgetNight() {
    BottomBarWidget(1, mBottomTabItems, {})
}

