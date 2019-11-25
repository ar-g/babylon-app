package ar_g.babylontest.features.posts.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar_g.babylontest.features.posts.detail.fakes.FakeErrorPostDetailOperations
import ar_g.babylontest.features.posts.detail.fakes.FakePostDetailOperations
import ar_g.babylontest.shared.ImmediateSchedulersProvider
import ar_g.babylontest.shared.ui.Lce
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
            FakePostDetailOperations(),
            ImmediateSchedulersProvider()
        )

        //then
        val title = FakePostDetailOperations.POST.title
        val body = FakePostDetailOperations.POST.body
        val firstPostDetail = PostDetailUiModel(title, body, "", "")
        val values = arrayOf(
            Lce.Content(firstPostDetail),
            Lce.Loading<PostDetailUiModel>(),
            Lce.Content(FakePostDetailOperations.POST_DETAIL)
        )
        viewModel.postDetailUiModel.assertValues(*values) {
            //when
            viewModel.getPostDetail(FakePostDetailOperations.POST)
        }
    }

    @Test
    fun getPostDetailReturnsCorrectStatesWhenErrorHappens() {

        //given
        val viewModel = PostDetailViewModel(
            FakeErrorPostDetailOperations(),
            ImmediateSchedulersProvider()
        )

        //then
        val title = FakePostDetailOperations.POST.title
        val body = FakePostDetailOperations.POST.body
        val firstPostDetail = PostDetailUiModel(title, body, "", "")
        val values = arrayOf(
            Lce.Content(firstPostDetail),
            Lce.Loading<PostDetailUiModel>(),
            Lce.Error(FakeErrorPostDetailOperations.ERROR_MSG)
        )
        viewModel.postDetailUiModel.assertValues(*values) {
            //when
            viewModel.getPostDetail(FakePostDetailOperations.POST)
        }
    }

    /*BEHAVIOR TESTS*/

    @Test
    fun getPostDetailNotCalledAgain() {
        //given
        val postsOperations = Mockito.spy(FakeErrorPostDetailOperations())
        val viewModel = PostDetailViewModel(postsOperations, ImmediateSchedulersProvider())
        val postUiModel = FakePostDetailOperations.POST

        //when
        viewModel.getPostDetail(postUiModel)
        viewModel.getPostDetail(postUiModel)

        //then
        Mockito.verify(postsOperations, Mockito.times(1)).getPostDetail(postUiModel)
    }
}

