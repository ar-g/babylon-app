package ar_g.babylontest.features.posts

import e.ar_g.babylontest.R
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.Post
import ar_g.babylontest.shared.api.model.User
import ar_g.babylontest.shared.res.ResStorage

class PostsMapperImpl(private val resStorage: ResStorage) : PostsMapper {

    override fun mapPosts(posts: List<Post>) = posts.map { post -> mapPost(post) }

    override fun mapPost(post: Post): PostUiModel {
        return PostUiModel(post.userId, post.id, post.title, post.body)
    }

    override fun mapPostDetail(postVM: PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel {
        val numberOfComments = if (comments.isNotEmpty()) resStorage.getString(R.string.posts_number_comments, comments.size) else ""
        return PostDetailUiModel(
            postVM.title,
            postVM.body,
            user.name,
            numberOfComments
        )
    }
}