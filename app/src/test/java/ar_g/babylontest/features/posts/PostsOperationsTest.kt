package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.fakes.FakeApi
import ar_g.babylontest.features.posts.fakes.FakeMapper
import ar_g.babylontest.shared.ImmediateSchedulersProvider
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.api.Api
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.*

class PostsOperationsTest {

    /*VERIFY TESTS*/

    @Test
    fun userAndCommentsCalledWithProperIds() {
        //given
        val api = spy(FakeApi())
        val mapper = mock(PostsMapper::class.java)
        val postsOperationsImpl =
            PostsOperationsImpl(api, mapper, ImmediateSchedulersProvider())

        //when
        val userId = 1
        val postId = 2
        postsOperationsImpl.getPostDetail(PostUiModel(userId, postId, "", ""))

        //then
        verify(api, times(1)).user(userId)
        verify(api, times(1)).comments(postId)
    }

    @Test
    fun mapperCalledWhenSinglesSucceed() {
        //given
        val api = FakeApi()
        val mapper = spy(FakeMapper())
        val postsOperationsImpl = PostsOperationsImpl(api, mapper,
            ImmediateSchedulersProvider()
        )

        //when
        val userId = 1
        val postId = 2
        val postUiModel = PostUiModel(userId, postId, "", "")
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
        val postsOperationsImpl = PostsOperationsImpl(api, mapper,
            ImmediateSchedulersProvider()
        )

        //when
        val userId = 1
        val postId = 2
        val postUiModel = PostUiModel(userId, postId, "", "")
        postsOperationsImpl.getPostDetail(postUiModel).subscribe({}, {})

        //then
        verify(mapper, times(0)).mapPostDetail(postUiModel, FakeApi.USER, emptyList())
    }
}

