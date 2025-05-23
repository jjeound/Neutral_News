package com.example.news.data.service

import com.example.news.data.dto.ApiResponse
import com.example.news.data.dto.NewsResponse
import com.example.news.data.dto.NewsInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {
    @GET("/clusters/hot")
    suspend fun getHotNewsList(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1,
    ): ApiResponse<NewsResponse>

    @GET("/clusters/latest")
    suspend fun getLatestNewsList(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1,
    ): ApiResponse<NewsResponse>

    @GET("/clusters/hot/category/{category}")
    suspend fun getCategoryHotNewsList(
        @Path("category") category: String,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1,
    ): ApiResponse<NewsResponse>

    @GET("/clusters/latest/category/{category}")
    suspend fun getCategoryLatestNewsList(
        @Path("category") category: String,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1,
    ): ApiResponse<NewsResponse>

    @GET("/clusters/{id}")
    suspend fun getNewsDetail(
        @Path("id") id: String,
    ): ApiResponse<NewsInfo>
}