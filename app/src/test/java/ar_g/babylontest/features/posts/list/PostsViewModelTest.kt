package ar_g.babylontest.features.posts.list

import ar_g.babylontest.features.posts.detail.fakes.FakeErrorPostDetailOperations
import ar_g.babylontest.shared.ui.Lce
import com.babylon.orbit.BaseOrbitContainer
import com.example.posts_sdk.PostsApi
import com.example.posts_sdk.core.wrapCall
import com.example.posts_sdk.domain.response.PostUiModel
import io.reactivex.observers.TestObserver
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class PostsMiddlewareTest : Spek({

    Feature("PostsViewModel") {
        val POST = PostUiModel(0, 0, "title", "body")
        val POSTS = mutableListOf(POST)
        val ERROR_MSG = "ERROR_MSG"

        val api by memoized { mock(PostsApi::class.java) }
        val postsMiddleware by memoized { PostsMiddleware(api) }
        val container by memoized { BaseOrbitContainer(postsMiddleware) }

        Scenario("getPosts success") {
            lateinit var stateObserver: TestObserver<Lce<List<PostUiModel>>>

            Given("connected container"){
                `when`(api.getPosts()).thenReturn(wrapCall { POSTS })
                stateObserver = container.orbit.test()
            }

            Then("states are correct") {
                val values = arrayOf(
                    Lce.Loading<List<PostUiModel>>(),
                    Lce.Content<List<PostUiModel>>(POSTS)
                )

                stateObserver.awaitCount(2).assertValues(*values)
            }
        }

        Scenario("getPosts error"){
            lateinit var stateObserver: TestObserver<Lce<List<PostUiModel>>>

            Given("connected container"){
                `when`(api.getPosts()).thenReturn(wrapCall { throw Throwable(ERROR_MSG) })
                stateObserver = container.orbit.test()
            }

            Then("states are correct") {
                val values = arrayOf(
                    Lce.Loading<List<PostUiModel>>(),
                    Lce.Error<List<PostUiModel>>(FakeErrorPostDetailOperations.ERROR_MSG)
                )

                stateObserver.awaitCount(2).assertValues(*values)
            }
        }
    }
})
