package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.User

interface PostsMapper {
    fun mapPost(post: com.example.posts_sdk.gateway.network.response.Post): com.example.posts_sdk.domain.response.PostUiModel
    fun mapPostDetail(postVM: com.example.posts_sdk.domain.response.PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel
}