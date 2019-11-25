package com.example.posts_sdk

import android.util.Log
import com.example.posts_sdk.core.Call
import com.example.posts_sdk.core.wrapCall
import com.example.posts_sdk.domain.response.PostUiModel
import com.example.posts_sdk.gateway.PostsGateway

internal class PostsApiImpl(
    private val postsGateway: PostsGateway
) : PostsApi {
    override fun getPosts(): Call<List<PostUiModel>> {
        return wrapCall {
            val posts = postsGateway.getPosts()
            Log.d("posts", posts.joinToString { "$it, " })
            posts
        }
    }
}