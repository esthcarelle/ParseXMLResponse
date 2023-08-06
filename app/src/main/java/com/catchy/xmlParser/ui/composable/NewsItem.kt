package com.catchy.xmlParser.ui.composable

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catchy.xmlParser.model.NewItem
import com.catchy.xmlParser.viewModel.NewsViewModel

@Composable
fun NewItem(newsItem: NewItem, onNavigate: (NewItem) -> Unit = {}, sharedViewModel: NewsViewModel) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        val description =
            Html.fromHtml(newsItem.description, Html.FROM_HTML_MODE_LEGACY)
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
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
            Text(
                text = description.toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
