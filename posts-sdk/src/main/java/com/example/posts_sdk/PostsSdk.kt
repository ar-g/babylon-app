package com.example.posts_sdk

import com.example.posts_sdk.gateway.NetworkPostsGateway
import com.example.posts_sdk.gateway.network.PostsService
import com.example.posts_sdk.gateway.network.mapping.PostsMapper

object PostsSdk{
    @JvmStatic
    val apiInstance: PostsApi by lazy {
        PostsApiImpl(
            NetworkPostsGateway(
                PostsService.create(),
                PostsMapper()
            )
        )
    }
}