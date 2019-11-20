package com.example.posts_sdk

import com.example.posts_sdk.core.wrapCall
import com.example.posts_sdk.gateway.PostsGateway

internal class PostsApiImpl(
    private val postsGateway: PostsGateway
) : PostsApi {
    override fun getPosts() = wrapCall {
        postsGateway.getPosts()
    }
}