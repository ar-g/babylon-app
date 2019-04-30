package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.shared.api.Api
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.Post
import ar_g.babylontest.shared.api.model.User
import io.reactivex.Single

open class FakeApi: Api {
    companion object {
        val USER = User("author")
    }

    override fun posts(): Single<List<Post>> {
        return Single.just(emptyList())
    }

    override fun user(userId: Int): Single<User> {
        return Single.just(USER)
    }

    override fun comments(postId: Int): Single<List<Comment>> {
        return Single.just(emptyList())
    }
}