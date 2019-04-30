package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.list.PostUiModel
import io.reactivex.Single

open class FakeErrorPostsOperations : PostsOperations {
    companion object{
        const val ERROR_MSG = "ERROR_MSG"
    }

    override fun getPosts(): Single<List<PostUiModel>> {
        return Single.error(Throwable(ERROR_MSG))
    }

    override fun getPostDetail(postUiModel: PostUiModel): Single<PostDetailUiModel> {
        return Single.error(Throwable(ERROR_MSG))
    }
}