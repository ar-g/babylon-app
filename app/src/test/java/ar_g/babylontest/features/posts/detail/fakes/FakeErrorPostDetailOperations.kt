package ar_g.babylontest.features.posts.detail.fakes

import ar_g.babylontest.features.posts.detail.usecase.PostDetailOperations
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import com.example.posts_sdk.domain.response.PostUiModel
import io.reactivex.Single

open class FakeErrorPostDetailOperations : PostDetailOperations {
    companion object{
        const val ERROR_MSG = "ERROR_MSG"
    }

    override fun getPostDetail(postUiModel: PostUiModel): Single<PostDetailUiModel> {
        return Single.error(Throwable(ERROR_MSG))
    }
}