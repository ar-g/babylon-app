package ar_g.babylontest.shared.api

import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.Post
import ar_g.babylontest.shared.api.model.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/posts")
    fun posts(): Single<List<Post>>

    @GET("/users/{id}")
    fun user(@Path("id") userId: Int): Single<User>

    @GET("/comments")
    fun comments(@Query("postId") postId: Int): Single<List<Comment>>

    companion object {
        fun create(): Api {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}