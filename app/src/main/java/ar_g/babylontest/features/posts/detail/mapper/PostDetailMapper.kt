package ar_g.babylontest.features.posts.detail.mapper

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.User

interface PostDetailMapper {
    fun mapPostDetail(postVM: com.example.posts_sdk.domain.response.PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel
}