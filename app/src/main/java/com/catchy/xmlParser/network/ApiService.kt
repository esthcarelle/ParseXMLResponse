@file:Suppress("DEPRECATION")

package com.catchy.xmlParser.network

import com.catchy.xmlParser.model.RssFeed
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("rss-feeds/news-english.xml")
     fun getNews(): Call<RssFeed>

    @Suppress("DEPRECATION")
    companion object{
        private const val URL = "https://www.who.int/"
        private lateinit var INSTANCE : ApiService

        fun getInstance(): ApiService{
               INSTANCE = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build().create(ApiService::class.java)

            return INSTANCE
        }
    }
}
