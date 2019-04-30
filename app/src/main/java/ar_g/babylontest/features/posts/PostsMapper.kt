package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.Post
import ar_g.babylontest.shared.api.model.User

interface PostsMapper {
    fun mapPosts(posts: List<Post>): List<PostUiModel>
    fun mapPost(post: Post): PostUiModel
    fun mapPostDetail(postVM: PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel
}