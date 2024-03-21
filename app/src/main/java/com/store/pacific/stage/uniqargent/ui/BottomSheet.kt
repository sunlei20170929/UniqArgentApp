package com.store.pacific.stage.uniqargent.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.updateBounds
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.store.pacific.stage.R


sealed class BottomSheet(
    val route: String,
    val iconSelect: Int,
//    val iconUnselect: Int,
) {
    object Home : BottomSheet(
        route = "home",
        iconSelect = R.mipmap.homedark,
//        iconUnselect = R.mipmap.homegray
    )

    object Got : BottomSheet(
        route = "Got",
        iconSelect = R.mipmap.gotgray,
//        iconUnselect = R.mipmap.gotgray
    )

    object Mine : BottomSheet(
        route = "Mine",
        iconSelect = R.mipmap.minegray,
//        iconUnselect = R.mipmap.minegray,
    )

}

@Composable
fun BottomContent(modifier: Modifier){
    val items = listOf(
        BottomSheet.Home,
        BottomSheet.Got,
        BottomSheet.Mine
    )

    val navController = rememberNavController()
    val context = LocalContext.current

    Scaffold(bottomBar = {}) {
        NavHost(
            navController,
            startDestination = BottomSheet.Home.route,
            modifier = Modifier.padding(it) 

        ) {
            composable(BottomSheet.Home.route) {
//                HomePage()
            }

            composable(BottomSheet.Got.route) {
                // FindPage()
            }

            composable(BottomSheet.Mine.route) {
                // MinePage()
            }


            BottomAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .drawWithCache {
                        val bgImg = ContextCompat.getDrawable(
                            context,
                            R.drawable.main_nav_bg
                        )
                        onDrawBehind {
                            bgImg!!.updateBounds(
                                0,
                                0, // 这里可以调整中间的大按钮的上下位置。
                                size.width.toInt(),
                                size.height.toInt()
                            )
                            bgImg.draw(drawContext.canvas.nativeCanvas)
                        }
                    }
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                var isSelected: Boolean
                items.forEach { screenPage ->
                    isSelected =
                        currentDestination?.hierarchy?.any { it.route == screenPage.route } == true
                    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                        BottomNavigationItem(
                            selected = isSelected,
                            selectedContentColor = Color(0xFF037FF5),
                            unselectedContentColor = Color(0xFF31373D),
                            onClick = {
                                navController.navigate(screenPage.route) {
                                    //点击Item时，清空栈内到NavOptionsBuilder.popUpTo ID之间的所有Item
                                    // 避免栈内节点的持续增加，同时saveState用于界面状态的恢复
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }

                                    // 避免多次点击Item时产生多个实列
                                    launchSingleTop = true
                                    // 当再次点击之前的Item时，恢复状态
                                    restoreState = true
                                }
                            },

                            icon = {
                                Image(
                                    painter = if (isSelected) {
                                        painterResource(screenPage.iconSelect)
                                    } else {
                                        painterResource(screenPage.iconUnselect)
                                    },
                                    null,
                                    modifier = if (!screenPage.isShowText) {
                                        Modifier.size(58.dp)
                                    } else {
                                        Modifier.size(25.dp)
                                    },
                                    contentScale = ContentScale.Crop
                                )
                            },
                            alwaysShowLabel = screenPage.isShowText,
                            label =
                            if (!screenPage.isShowText) {
                                null
                            } else {
                                {
                                    Text(
                                        text = stringResource(screenPage.resId),
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (isSelected) {
                                                Color.Yellow
                                            } else {
                                                Color.Black
                                            }
                                        )
                                    )
                                }
                            },
                            modifier = if (screenPage.isShowText) {
                                Modifier.padding(top = 10.dp)
                            } else {
                                Modifier.padding(top = 0.dp)
                            }
                        )
                    }
                }
            }

            
            )

        }


    }

}

@Preview
@Composable
fun showBottmBar(){
    BottomContent(modifier = Modifier)
}


