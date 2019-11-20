package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.features.posts.detail.usecase.PostDetailOperations
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import com.example.posts_sdk.domain.response.PostUiModel
import io.reactivex.Single

class FakePostDetailOperations : PostDetailOperations {
    companion object{
        val POST = PostUiModel(0, 0, "title", "body")
        val POSTS = mutableListOf(POST)
        val POST_DETAIL =
            PostDetailUiModel("title", "body", "author", "5 comments")
    }

    fun getPosts(): Single<List<PostUiModel>> {
        return Single.just(POSTS)
    }

    override fun getPostDetail(postUiModel: PostUiModel): Single<PostDetailUiModel> {
        return Single.just(POST_DETAIL)
    }
}