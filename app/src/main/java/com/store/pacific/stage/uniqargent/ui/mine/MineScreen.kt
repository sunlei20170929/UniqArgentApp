package com.store.pacific.stage.uniqargent.ui.mine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.withContext

@Composable
fun MineScreen(){
    val viewModel = viewModel<MineViewModel>()

    val lifecycleOwner = LocalLifecycleOwner.current

//    LaunchedEffect(lifecycleOwner.lifecycle){
//        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
//            viewModel.navigationEventsChannelFlow.collect{event->
//                when(event){
//                    is MineViewModel.NavigationEvent.NavigateToProfile->{
////                        navController.navigate()
//                    }
//                }
//
//            }
//        }
//    }

    ObserveAsState(viewModel.navigationEventsChannelFlow){event->
        when(event){
                    is MineViewModel.NavigationEvent.NavigateToProfile->{
//                        navController.navigate()
                    }
                }
    }
}


@Composable
private fun <T> ObserveAsState(flow: Flow<T>, onEvent:(T)->Unit){
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(flow,lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate){
                flow.collect(onEvent)
            }
        }
    }
}