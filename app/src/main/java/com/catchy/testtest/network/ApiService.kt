package com.catchy.testtest.network

import com.catchy.testtest.model.RssFeed
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("rss-feeds/news-english.xml")
    suspend fun getNews(): Call<RssFeed>

    companion object{
        const val URL = "https://www.who.int/"
        lateinit var INSTANCE : ApiService

        fun getInstance(): ApiService{
               INSTANCE = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build().create(ApiService::class.java)

            return INSTANCE
        }
    }
}
