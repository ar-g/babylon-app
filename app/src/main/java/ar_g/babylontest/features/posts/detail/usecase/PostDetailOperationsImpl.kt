package ar_g.babylontest.features.posts.detail.usecase

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.detail.mapper.PostDetailMapper
import com.example.posts_sdk.domain.response.PostUiModel
import ar_g.babylontest.shared.rx.SchedulersProvider
import ar_g.babylontest.shared.api.Api
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.User
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class PostDetailOperationsImpl(
    private val api: Api,
    private val postDetailMapper: PostDetailMapper,
    private val schedulersProvider: SchedulersProvider
) : PostDetailOperations {

    override fun getPostDetail(postUiModel: PostUiModel): Single<PostDetailUiModel> {
        return Single.zip(
            api.user(postUiModel.userId)
                .subscribeOn(schedulersProvider.io()),
            api.comments(postUiModel.postId)
                .subscribeOn(schedulersProvider.io()),
            BiFunction { user: User, comments: List<Comment> ->
                postDetailMapper.mapPostDetail(postUiModel, user, comments)
            }
        ).subscribeOn(schedulersProvider.io())
    }
}