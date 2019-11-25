package ar_g.babylontest.features.posts.detail.usecase

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import io.reactivex.Single

interface PostDetailOperations {
    fun getPostDetail(postUiModel: com.example.posts_sdk.domain.response.PostUiModel): Single<PostDetailUiModel>
}