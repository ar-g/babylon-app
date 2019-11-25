package com.example.posts_sdk.gateway.network

import com.example.posts_sdk.gateway.network.response.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostsService {

    @GET("/posts")
    suspend fun posts(): List<Post>

    companion object {
        fun create(): PostsService {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostsService::class.java)
        }
    }
}