package com.catchy.testtest.ui.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.catchy.testtest.model.NewItem
import com.catchy.testtest.viewModel.NewsViewModel

@Composable
fun News(onNavigate: (NewItem) -> Unit = {}, sharedViewModel: NewsViewModel) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        sharedViewModel.fetchedList.channel?.items?.let {
            items(it) { newsItem ->
                NewItem(
                    newsItem = newsItem,
                    onNavigate = onNavigate,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}
