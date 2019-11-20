package ar_g.babylontest.features.posts.detail.mapper

import e.ar_g.babylontest.R
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.User
import ar_g.babylontest.shared.res.ResStorage

class PostDetailMapperImpl(private val resStorage: ResStorage) :
    PostDetailMapper {
    override fun mapPostDetail(postVM: com.example.posts_sdk.domain.response.PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel {
        val numberOfComments = if (comments.isNotEmpty()) resStorage.getString(R.string.posts_number_comments, comments.size) else ""
        return PostDetailUiModel(
            postVM.title,
            postVM.body,
            user.name,
            numberOfComments
        )
    }
}