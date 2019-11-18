package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import com.example.posts_sdk.domain.response.PostUiModel
import io.reactivex.Single

interface PostsOperations {
    fun getPosts(): Single<List<com.example.posts_sdk.domain.response.PostUiModel>>
    fun getPostDetail(postUiModel: com.example.posts_sdk.domain.response.PostUiModel): Single<PostDetailUiModel>
}