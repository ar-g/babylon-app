package ar_g.babylontest.features.posts.detail.fakes

import ar_g.babylontest.features.posts.detail.mapper.PostDetailMapper
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.User
import com.example.posts_sdk.domain.response.PostUiModel

open class FakeMapper : PostDetailMapper {
    companion object{
        val POST_DETAIL =
            PostDetailUiModel("title", "body", "author", "5 comments")
    }

    override fun mapPostDetail(postVM: PostUiModel, user: User, comments: List<Comment>): PostDetailUiModel {
        return POST_DETAIL
    }
}