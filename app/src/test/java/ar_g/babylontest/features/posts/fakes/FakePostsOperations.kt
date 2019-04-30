package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.list.PostUiModel
import io.reactivex.Single

class FakePostsOperations : PostsOperations {
    companion object{
        val POST = PostUiModel(0, 0, "title", "body")
        val POSTS = mutableListOf(POST)
        val POST_DETAIL =
            PostDetailUiModel("title", "body", "author", "5 comments")
    }

    override fun getPosts(): Single<List<PostUiModel>> {
        return Single.just(POSTS)
    }

    override fun getPostDetail(postUiModel: PostUiModel): Single<PostDetailUiModel> {
        return Single.just(POST_DETAIL)
    }
}