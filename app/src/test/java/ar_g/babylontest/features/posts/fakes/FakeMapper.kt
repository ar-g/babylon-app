package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.features.posts.PostsMapper
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.Post
import ar_g.babylontest.shared.api.model.User

open class FakeMapper : PostsMapper {
    companion object{
        val POST = PostUiModel(0, 0, "title", "body")
        val POSTS = mutableListOf(POST)
        val POST_DETAIL =
            PostDetailUiModel("title", "body", "author", "5 comments")
    }

    override fun mapPosts(posts: List<Post>): List<PostUiModel> {
        return POSTS
    }

    override fun mapPost(post: Post): PostUiModel {
        return POST
    }

    override fun mapPostDetail(postVM: PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel {
        return POST_DETAIL
    }
}