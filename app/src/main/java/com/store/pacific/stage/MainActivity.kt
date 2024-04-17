package com.store.pacific.stage

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.store.pacific.stage.ui.theme.UniqArgentTheme
import com.store.pacific.stage.uniqargent.ui.FlashScreen
import com.store.pacific.stage.uniqargent.ui.product.MainNav

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniqArgentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val mainViewModel = hiltViewModel<MainViewModel>()
                    val mainViewModel:MainViewModel = viewModel(factory = MainViewModel.Factory)
                    val transitionState = remember { MutableTransitionState(mainViewModel.shownSplash.value) }
                    val transition = updateTransition(transitionState, label = "splashTransition")
                    val splashAlpha by transition.animateFloat(
                        transitionSpec = { tween(durationMillis = 100) }, label = "splashAlpha"
                    ) {
                        if (it == SplashState.Shown) 1f else 0f
                    }
                    val contentAlpha by transition.animateFloat(
                        transitionSpec = { tween(durationMillis = 300) }, label = "contentAlpha"
                    ) {
                        if (it == SplashState.Shown) 0f else 1f
                    }
                    val contentTopPadding by transition.animateDp(
                        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = "contentTopPadding"
                    ) {
                        if (it == SplashState.Shown) 100.dp else 0.dp
                    }

                    Box {
                        FlashScreen(Modifier.alpha(splashAlpha),
                            onTimeout={
                                  transitionState.targetState = SplashState.Completed
                                  mainViewModel.shownSplash.value = SplashState.Completed

                            })
                        MainNav(Modifier.alpha(contentAlpha),
                            topPadding = contentTopPadding,
                            mainViewModel)
                    }

                }
            }
        }
    }
}
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UniqArgentTheme {

    }
}

enum class SplashState { Shown, Completed }