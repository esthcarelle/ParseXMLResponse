package com.catchy.testtest

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catchy.testtest.model.NewItem
import com.catchy.testtest.ui.theme.TestTestTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.core.text.HtmlCompat
import com.catchy.testtest.navigation.NavGraph
import com.catchy.testtest.viewModel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}


@Composable
fun News(onNavigate: (NewItem) -> Unit = {},sharedViewModel: NewsViewModel) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        sharedViewModel.fetchedList.channel?.items?.let {
            items(it) { newsItem ->
                NewItem(newsItem = newsItem,onNavigate = onNavigate,sharedViewModel = sharedViewModel)
            }
        }

    }
}
@Composable
fun NewItem(newsItem: NewItem, onNavigate: (NewItem) -> Unit = {},sharedViewModel: NewsViewModel){
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).clickable {
                sharedViewModel.selectedNews = newsItem
                onNavigate.invoke(newsItem)

            }
        ) {
            newsItem.title?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            newsItem.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun NewsDetail(newsItem: NewItem){
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        val description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(newsItem.description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            newsItem.description?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        }
        Column(
            modifier = Modifier.padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            newsItem.title?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            newsItem.pubDate?.let {
                Text(
                    text = "Publication date:$it",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
               Text(
                    text = description.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTestTheme {
    }
}