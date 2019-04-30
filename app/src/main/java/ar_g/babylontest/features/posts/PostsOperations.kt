package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.list.PostUiModel
import io.reactivex.Single

interface PostsOperations {
    fun getPosts(): Single<List<PostUiModel>>
    fun getPostDetail(postUiModel: PostUiModel): Single<PostDetailUiModel>
}