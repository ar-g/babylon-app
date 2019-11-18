package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.features.posts.PostsMapper
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import com.example.posts_sdk.domain.response.PostUiModel
import ar_g.babylontest.shared.api.model.Comment
import com.example.posts_sdk.gateway.network.response.Post
import ar_g.babylontest.shared.api.model.User

open class FakeMapper : PostsMapper {
    companion object{
        val POST = com.example.posts_sdk.domain.response.PostUiModel(0, 0, "title", "body")
        val POSTS = mutableListOf(POST)
        val POST_DETAIL =
            PostDetailUiModel("title", "body", "author", "5 comments")
    }

    fun mapPosts(posts: List<com.example.posts_sdk.gateway.network.response.Post>): List<com.example.posts_sdk.domain.response.PostUiModel> {
        return POSTS
    }

    override fun mapPost(post: com.example.posts_sdk.gateway.network.response.Post): com.example.posts_sdk.domain.response.PostUiModel {
        return POST
    }

    override fun mapPostDetail(postVM: com.example.posts_sdk.domain.response.PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel {
        return POST_DETAIL
    }
}