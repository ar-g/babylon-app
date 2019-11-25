package com.example.posts_sdk.gateway.network.mapping

import com.example.posts_sdk.domain.response.PostUiModel
import com.example.posts_sdk.gateway.network.response.Post

internal class PostsMapper {
    fun toUiPosts(posts: List<Post>) = posts.map { post ->
        toUiPost(post)
    }

    fun toUiPost(post: Post) =
        PostUiModel(post.userId, post.id, post.title, post.body)
}