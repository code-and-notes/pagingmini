package com.android.learning.pagingmini.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.learning.pagingmini.ui.ProductsScreen
import com.android.learning.pagingmini.ui.MainViewModel
import com.android.learning.pagingmini.ui.theme.PagingminiTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.Alignment
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.learning.pagingmini.ui.sealed.FeedType
import kotlin.collections.emptyList


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val  mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagingminiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        mainViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Home(name: String, modifier: Modifier = Modifier,mainViewModel: MainViewModel) {
    var showProducts by remember { mutableStateOf(false) }
    var feedType: FeedType by remember { mutableStateOf(FeedType.RemoteOnly) }
    var expanded by remember { mutableStateOf(false) }
    val products = when(feedType){
        FeedType.OfflineFirst -> mainViewModel.getAllProductsFromCache().collectAsLazyPagingItems()
        FeedType.RemoteOnly ->
        mainViewModel.getAllProductsFromNetwork().collectAsLazyPagingItems()}

    Column(modifier = modifier.fillMaxSize().padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Button(onClick = {expanded = true}) {Text(feedType.name) }
            DropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
                DropdownMenuItem(text = { Text("Remote Only") }, onClick = {
                    feedType = FeedType.RemoteOnly
                    expanded = false
                })
                DropdownMenuItem(text = { Text("Offline first") }, onClick = {
                    feedType = FeedType.OfflineFirst
                    expanded = false
                })
            }
        }

        Button(onClick = {
            Log.w("MainActivity", "Button Clicked")
            showProducts = when(feedType){
                FeedType.RemoteOnly -> {
                    true
                }

                FeedType.OfflineFirst -> {
                    mainViewModel.getAndInsertProducts()
                }
            }

        }) {
            Text("Get And Save Products")
        }

        if(showProducts){
            ProductsScreen(productsList = products)
        }



    }
}
