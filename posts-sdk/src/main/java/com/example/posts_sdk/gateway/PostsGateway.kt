package com.example.posts_sdk.gateway

import com.example.posts_sdk.domain.response.PostUiModel

internal interface PostsGateway {
    suspend fun getPosts() : List<PostUiModel>
}