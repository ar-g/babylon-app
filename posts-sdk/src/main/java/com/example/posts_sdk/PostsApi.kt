package com.example.posts_sdk

import androidx.annotation.CheckResult
import com.example.posts_sdk.domain.response.PostUiModel
import com.example.posts_sdk.gateway.network.PostsService
import com.example.posts_sdk.gateway.network.response.Post

//todo learning strategy for the whole project
//todo add sdkV2 usage to ui layer

//todo read the docs around testing
//todo go through the tests with Sergi
object PostsSdk{
    @JvmStatic
    val apiInstance: PostsApi by lazy {
        PostsApiImpl(
            NetworkPostsGateway(
                PostsService.create(),
                PostsMapper()
            )
        )
    }
}

interface PostsApi{
    fun getPosts() : Call<List<PostUiModel>>
}

internal class PostsApiImpl(
    private val postsGateway: PostsGateway
) : PostsApi {
    override fun getPosts(): Call<List<PostUiModel>> {
        return //postsGateway.getPosts()
    }
}

internal interface PostsGateway {
    suspend fun getPosts() : List<PostUiModel>
}

internal class NetworkPostsGateway(
    private val postsService: PostsService,
    private val postsMapper: PostsMapper
) : PostsGateway {
    override suspend fun getPosts(): List<PostUiModel> {
        val posts = postsService.posts()
        return postsMapper.toUiPosts(posts)
    }
}

internal class PostsMapper {
    fun toUiPosts(posts: List<Post>) = posts.map { post ->
        PostUiModel(post.userId, post.id, post.title, post.body)
    }
}

interface Call<T> {

    /**
     * Whether the call has been cancelled. This is set to true immediately after the
     * first call to [cancel]
     */
    val cancelled: Boolean

    /**
     * Whether the call has been already executed. This is set to true immediately after the
     * first call to [execute] or [enqueue]
     */
    val executed: Boolean

    /**
     * Whether the call will relay any [AuthenticationException] thrown within to the global error relay
     * available through the Core SDK.
     */
    val emitGlobalAuthErrors: Boolean

    /**
     * Suppress the delivery of exceptions to the global error relay available through the Core SDK.
     */
    @CheckResult
    fun suppressGlobalAuthErrors(): Call<T>

    /**
     * Cancels an in-progress call
     */
    fun cancel()

    /**
     * Executes the call synchronously, blocking the current thread until the operation completes.
     *
     * @throws [IllegalStateException] if the [Call] has already been run before
     * @throws [CancellationException] if the [Call] has been previously cancelled
     * @return the result of the [Call]
     */
    fun execute(): T

    /**
     * Executes the call asynchronously, running the operation on an IO thread. The results are
     * then delivered on the Android main thread.
     *
     * @param callback
     *
     * @throws [IllegalStateException] if the [Call] has already been run before
     * @throws [CancellationException] if the [Call] has been previously cancelled
     */
    fun enqueue(callback: Callback<T>)
}


interface Callback<T> {
    /**
     * Delivers the result when the [Call] completes successfully
     *
     * @param data the result of the [Call]
     */
    fun onResult(data: T)

    /**
     * Called whenever the [Call] is unsuccessful.
     *
     * @param throwable the cause of the failure
     */
    fun onException(throwable: Throwable)
}

