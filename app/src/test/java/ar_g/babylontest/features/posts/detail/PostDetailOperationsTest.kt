package ar_g.babylontest.features.posts.detail

import ar_g.babylontest.features.posts.detail.mapper.PostDetailMapper
import ar_g.babylontest.features.posts.detail.usecase.PostDetailOperationsImpl
import ar_g.babylontest.features.posts.detail.fakes.FakeApi
import ar_g.babylontest.features.posts.detail.fakes.FakeMapper
import ar_g.babylontest.shared.ImmediateSchedulersProvider
import ar_g.babylontest.shared.api.Api
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.*

class PostDetailOperationsTest {

    /*VERIFY TESTS*/

    @Test
    fun userAndCommentsCalledWithProperIds() {
        //given
        val api = spy(FakeApi())
        val mapper = mock(PostDetailMapper::class.java)
        val postsOperationsImpl =
            PostDetailOperationsImpl(
                api,
                mapper,
                ImmediateSchedulersProvider()
            )

        //when
        val userId = 1
        val postId = 2
        postsOperationsImpl.getPostDetail(com.example.posts_sdk.domain.response.PostUiModel(userId, postId, "", ""))

        //then
        verify(api, times(1)).user(userId)
        verify(api, times(1)).comments(postId)
    }

    @Test
    fun mapperCalledWhenSinglesSucceed() {
        //given
        val api = FakeApi()
        val mapper = spy(FakeMapper())
        val postsOperationsImpl = PostDetailOperationsImpl(
            api, mapper,
            ImmediateSchedulersProvider()
        )

        //when
        val userId = 1
        val postId = 2
        val postUiModel = com.example.posts_sdk.domain.response.PostUiModel(userId, postId, "", "")
        postsOperationsImpl.getPostDetail(postUiModel).subscribe()

        //then
        verify(mapper, times(1)).mapPostDetail(postUiModel, FakeApi.USER, emptyList())
    }

    @Test
    fun mapperIsNotCalledWhenOneSingleFails() {
        //given

        //mock use for demo purpose
        val api = mock(Api::class.java)
        `when`(api.comments(anyInt())).thenReturn(Single.just(emptyList()))
        `when`(api.user(anyInt())).thenReturn(Single.error(Throwable()))
        val mapper = spy(FakeMapper())
        val postsOperationsImpl = PostDetailOperationsImpl(
            api, mapper,
            ImmediateSchedulersProvider()
        )

        //when
        val userId = 1
        val postId = 2
        val postUiModel = com.example.posts_sdk.domain.response.PostUiModel(userId, postId, "", "")
        postsOperationsImpl.getPostDetail(postUiModel).subscribe({}, {})

        //then
        verify(mapper, times(0)).mapPostDetail(postUiModel, FakeApi.USER, emptyList())
    }
}

