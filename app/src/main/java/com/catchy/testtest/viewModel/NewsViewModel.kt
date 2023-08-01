package com.catchy.testtest.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.catchy.testtest.model.RssFeed
import com.catchy.testtest.network.ApiService.Companion.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel : ViewModel() {

    private var _fetchedList by mutableStateOf<RssFeed>(RssFeed())
    val fetchedList: RssFeed
        get() = _fetchedList

    init {
        fetchList()
    }

    private fun fetchList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = getInstance()
                _fetchedList = apiService.getNews()

            } catch (e: Exception) {
                Log.e(TAG, "fetchList: " + e.message)
            }
        }
    }
}