package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import com.example.posts_sdk.domain.response.PostUiModel
import ar_g.babylontest.shared.rx.SchedulersProvider
import ar_g.babylontest.shared.api.Api
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.User
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class PostsOperationsImpl(
    private val api: Api,
    private val postsMapper: PostsMapper,
    private val schedulersProvider: SchedulersProvider
) : PostsOperations {

    override fun getPosts(): Single<List<com.example.posts_sdk.domain.response.PostUiModel>> {
        return api.posts()
            .map(postsMapper::mapPosts)
            .subscribeOn(schedulersProvider.io())
    }

    override fun getPostDetail(postUiModel: com.example.posts_sdk.domain.response.PostUiModel): Single<PostDetailUiModel> {
        return Single.zip(
            api.user(postUiModel.userId)
                .subscribeOn(schedulersProvider.io()),
            api.comments(postUiModel.postId)
                .subscribeOn(schedulersProvider.io()),
            BiFunction { user: User, comments: List<Comment> ->
                postsMapper.mapPostDetail(postUiModel, user, comments)
            }
        ).subscribeOn(schedulersProvider.io())
    }
}