package com.example.posts_sdk

import com.example.posts_sdk.core.Call
import com.example.posts_sdk.domain.response.PostUiModel

interface PostsApi{
    fun getPosts() : Call<List<PostUiModel>>
}


