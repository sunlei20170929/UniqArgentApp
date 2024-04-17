package com.store.pacific.stage.uniqargent.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T> PullToRefreshLazyColumn(data:List<T>,
                                content:@Composable (T)->Unit,
                                isRefreshing:Boolean,
                                onRefresh:()->Unit,
                                modifier: Modifier = Modifier,
                                lazyListState:LazyListState = rememberLazyListState()){
//    val pullToRefreshState = rememberPullRefreshState(refreshing = , onRefresh = { /*TODO*/ })
    val pullToRefreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)
    ){

        LazyColumn(state = lazyListState,
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            items(data){
                content(it)
            }
        }

        if(pullToRefreshState.isRefreshing){
            LaunchedEffect(true){
                onRefresh()
            }

        }

        LaunchedEffect(isRefreshing){
            if(isRefreshing){
                pullToRefreshState.startRefresh()
            }else{
                pullToRefreshState.endRefresh()
            }
        }

        PullToRefreshContainer(state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter))
    }

}