package com.example.uppercutu.api

import com.example.uppercutu.modelo.news.BoxingNews
import com.example.uppercutu.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    //Construyo la url para sacar la info de la api
    @GET("v2/everything")
    suspend fun  boxingNews(
        @Query("q")
        query: String ="MMA",
        @Query("pageSize")
        pageSize: Int =50,
        @Query("Page")
        pageNumber: Int =1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<BoxingNews>

    @GET("v2/everything")
    suspend fun boxingNewsEsp(
        @Query("q")
        query: String = "MMA",
        @Query("language")
        language: String = "es",
        @Query("pageSize")
        pageSize: Int = 20,
        @Query("page")
        pageNumber: Int = 1,
        @Query("sortBy")
        sortBy: String = "popularity",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<BoxingNews>

    @GET("v2/everything")
    suspend fun  searchForNews(
        @Query("q")
        query: String,
        @Query("pageSize")
        pageSize: Int =20,
        @Query("Page")
        pageNumber: Int =1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<BoxingNews>
}