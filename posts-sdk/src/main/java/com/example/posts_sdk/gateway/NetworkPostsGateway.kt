package com.example.posts_sdk.gateway

import com.example.posts_sdk.domain.response.PostUiModel
import com.example.posts_sdk.gateway.network.PostsService
import com.example.posts_sdk.gateway.network.mapping.PostsMapper

internal class NetworkPostsGateway(
    private val postsService: PostsService,
    private val postsMapper: PostsMapper
) : PostsGateway {
    override suspend fun getPosts(): List<PostUiModel> {
        val posts = postsService.posts()
        return postsMapper.toUiPosts(posts)
    }
}