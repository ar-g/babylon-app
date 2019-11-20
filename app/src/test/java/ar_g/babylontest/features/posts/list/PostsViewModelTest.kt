package ar_g.babylontest.features.posts.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar_g.babylontest.features.posts.fakes.FakeErrorPostDetailOperations
import ar_g.babylontest.features.posts.fakes.FakePostDetailOperations
import ar_g.babylontest.shared.ImmediateSchedulersProvider
import ar_g.babylontest.shared.ui.Lce
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.spekframework.spek2.Spek

class PostsViewModelTest: Spek({

}) {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    /*STATE TESTS*/

    @Test
    fun getPostsReturnsCorrectStates() {

        //given
        val viewModel = PostsViewModel(FakePostDetailOperations(), ImmediateSchedulersProvider())

        //then
        val values = arrayOf(
            Lce.Loading<List<com.example.posts_sdk.domain.response.PostUiModel>>(),
            Lce.Content<List<com.example.posts_sdk.domain.response.PostUiModel>>(FakePostDetailOperations.POSTS)
        )
        viewModel.postsUiModels.assertValues(*values) {
            //when
            viewModel.getPosts()
        }
    }

    @Test
    fun getPostsReturnsCorrectStatesWhenErrorHappens() {
        val viewModel = PostsViewModel(
            FakeErrorPostDetailOperations(),
            ImmediateSchedulersProvider()
        )

        //then
        val values = arrayOf(
            Lce.Loading<List<com.example.posts_sdk.domain.response.PostUiModel>>(),
            Lce.Error<List<com.example.posts_sdk.domain.response.PostUiModel>>(FakeErrorPostDetailOperations.ERROR_MSG)
        )
        viewModel.postsUiModels.assertValues(*values) {
            //when
            viewModel.getPosts()
        }
    }

    /*BEHAVIOR TESTS*/

    @Test
    fun getPostsNotCalledAgain() {
        //given
        val postsOperations = spy(FakeErrorPostDetailOperations())
        val viewModel = PostsViewModel(postsOperations, ImmediateSchedulersProvider())

        //when
        viewModel.getPosts()
        viewModel.getPosts()

        //then
        verify(postsOperations, times(1)).getPosts()
    }
}

