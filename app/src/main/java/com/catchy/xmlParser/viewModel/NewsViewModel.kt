package com.catchy.xmlParser.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.catchy.xmlParser.model.NewItem
import com.catchy.xmlParser.model.RssFeed
import com.catchy.xmlParser.network.ApiService.Companion.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel : ViewModel() {

    private var _fetchedList by mutableStateOf(RssFeed())
    var selectedNews: NewItem? = null

    val fetchedList: RssFeed
        get() = _fetchedList

    init {
        fetchList()
    }

    private fun fetchList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getInstance()
                apiService.getNews().execute().body()?.let {
                    _fetchedList = it
                }
            } catch (e: Exception) {
                Log.e(TAG, "fetchList: " + e.message)
            }
        }
    }
}