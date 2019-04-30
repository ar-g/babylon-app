package ar_g.babylontest.features.posts.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar_g.babylontest.features.posts.fakes.FakeErrorPostsOperations
import ar_g.babylontest.features.posts.fakes.FakePostsOperations
import ar_g.babylontest.shared.ImmediateSchedulersProvider
import ar_g.babylontest.shared.Lce
import ar_g.babylontest.shared.assertValues
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class PostDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    /*STATE TESTS*/

    @Test
    fun getPostDetailReturnsCorrectStates() {

        //given
        val viewModel = PostDetailViewModel(
            FakePostsOperations(),
            ImmediateSchedulersProvider()
        )

        //then
        val title = FakePostsOperations.POST.title
        val body = FakePostsOperations.POST.body
        val firstPostDetail = PostDetailUiModel(title, body, "", "")
        val values = arrayOf(
            Lce.Content(firstPostDetail),
            Lce.Loading<PostDetailUiModel>(),
            Lce.Content(FakePostsOperations.POST_DETAIL)
        )
        viewModel.postDetailUiModel.assertValues(*values) {
            //when
            viewModel.getPostDetail(FakePostsOperations.POST)
        }
    }

    @Test
    fun getPostDetailReturnsCorrectStatesWhenErrorHappens() {

        //given
        val viewModel = PostDetailViewModel(
            FakeErrorPostsOperations(),
            ImmediateSchedulersProvider()
        )

        //then
        val title = FakePostsOperations.POST.title
        val body = FakePostsOperations.POST.body
        val firstPostDetail = PostDetailUiModel(title, body, "", "")
        val values = arrayOf(
            Lce.Content(firstPostDetail),
            Lce.Loading<PostDetailUiModel>(),
            Lce.Error(FakeErrorPostsOperations.ERROR_MSG)
        )
        viewModel.postDetailUiModel.assertValues(*values) {
            //when
            viewModel.getPostDetail(FakePostsOperations.POST)
        }
    }

    /*BEHAVIOR TESTS*/

    @Test
    fun getPostDetailNotCalledAgain() {
        //given
        val postsOperations = Mockito.spy(FakeErrorPostsOperations())
        val viewModel = PostDetailViewModel(postsOperations, ImmediateSchedulersProvider())
        val postUiModel = FakePostsOperations.POST

        //when
        viewModel.getPostDetail(postUiModel)
        viewModel.getPostDetail(postUiModel)

        //then
        Mockito.verify(postsOperations, Mockito.times(1)).getPostDetail(postUiModel)
    }
}

