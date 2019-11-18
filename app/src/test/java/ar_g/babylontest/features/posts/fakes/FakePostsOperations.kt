package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import com.example.posts_sdk.domain.response.PostUiModel
import io.reactivex.Single

class FakePostsOperations : PostsOperations {
    companion object{
        val POST = com.example.posts_sdk.domain.response.PostUiModel(0, 0, "title", "body")
        val POSTS = mutableListOf(POST)
        val POST_DETAIL =
            PostDetailUiModel("title", "body", "author", "5 comments")
    }

    override fun getPosts(): Single<List<com.example.posts_sdk.domain.response.PostUiModel>> {
        return Single.just(POSTS)
    }

    override fun getPostDetail(postUiModel: com.example.posts_sdk.domain.response.PostUiModel): Single<PostDetailUiModel> {
        return Single.just(POST_DETAIL)
    }
}