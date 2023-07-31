package com.catchy.testtest

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catchy.testtest.model.RssFeed
import com.catchy.testtest.network.ApiService.Companion.getInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel : ViewModel() {

    val fetchedList = mutableListOf<RssFeed>()

    init {
        fetchList()
    }

    fun fetchList() {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val apiService = getInstance()
                apiService.getNews()
            }catch (e: Exception){
                Log.e(TAG, "fetchList: "+e.message )
            }
        }
    }
}