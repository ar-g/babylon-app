package ar_g.babylontest.features.posts.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar_g.babylontest.features.posts.fakes.FakeErrorPostsOperations
import ar_g.babylontest.features.posts.fakes.FakePostsOperations
import ar_g.babylontest.shared.ImmediateSchedulersProvider
import ar_g.babylontest.shared.Lce
import ar_g.babylontest.shared.assertValues
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class PostsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    /*STATE TESTS*/

    @Test
    fun getPostsReturnsCorrectStates() {

        //given
        val viewModel = PostsViewModel(FakePostsOperations(), ImmediateSchedulersProvider())

        //then
        val values = arrayOf(
            Lce.Loading<List<PostUiModel>>(),
            Lce.Content<List<PostUiModel>>(FakePostsOperations.POSTS)
        )
        viewModel.postsUiModels.assertValues(*values) {
            //when
            viewModel.getPosts()
        }
    }

    @Test
    fun getPostsReturnsCorrectStatesWhenErrorHappens() {
        val viewModel = PostsViewModel(
            FakeErrorPostsOperations(),
            ImmediateSchedulersProvider()
        )

        //then
        val values = arrayOf(
            Lce.Loading<List<PostUiModel>>(),
            Lce.Error<List<PostUiModel>>(FakeErrorPostsOperations.ERROR_MSG)
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
        val postsOperations = spy(FakeErrorPostsOperations())
        val viewModel = PostsViewModel(postsOperations, ImmediateSchedulersProvider())

        //when
        viewModel.getPosts()
        viewModel.getPosts()

        //then
        verify(postsOperations, times(1)).getPosts()
    }
}

