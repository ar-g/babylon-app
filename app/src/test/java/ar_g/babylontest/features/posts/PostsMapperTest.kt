package ar_g.babylontest.features.posts

import ar_g.babylontest.features.posts.detail.PostDetailUiModel
import ar_g.babylontest.features.posts.fakes.FakeResStorage
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.api.model.Comment
import ar_g.babylontest.shared.api.model.Post
import ar_g.babylontest.shared.api.model.User
import e.ar_g.babylontest.R
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

class PostsMapperTest {

    /*STATE TESTS*/

    private val title = "title"
    private val body = "body"
    private val name = "author"
    private val userId = 0
    private val postId = 0

    @Test
    fun mapPostCorrectly() {
        //given
        val postsMapper = PostsMapperImpl(FakeResStorage())
        val post = Post(userId, title, body, userId)

        //when
        val postUiModel = postsMapper.mapPost(post)

        //then
        val expectedPostUiModel = PostUiModel(userId, postId, title, body)
        assertEquals(expectedPostUiModel, postUiModel)
    }

    @Test
    fun mapPostsCorrectly() {
        //given
        val postsMapper = PostsMapperImpl(FakeResStorage())
        val post = Post(userId, title, body, userId)

        //when
        val postUiModels = postsMapper.mapPosts(mutableListOf(post))

        //then
        val expectedUIModels = mutableListOf(PostUiModel(userId, postId, title, body))
        assertEquals(expectedUIModels, postUiModels)
    }

    @Test
    fun mapPostDetailCorrectly() {
        //given
        val postsMapper = PostsMapperImpl(FakeResStorage())
        val postUiModel = PostUiModel(userId, postId, title, body)
        val user = User(name)
        val comments = mutableListOf(Comment())

        //when
        val postDetailUiModel = postsMapper.mapPostDetail(postUiModel, user, comments)

        //then
        val expectedPostDetailUiModel = PostDetailUiModel(title, body, name, FakeResStorage.RETURN_STRING)
        assertEquals(expectedPostDetailUiModel, postDetailUiModel)
    }


    /*VERIFY TESTS*/
    @Test
    fun resStorageCalledWithProperArguments() {
        //given
        val fakeResStorage = spy(FakeResStorage())
        val postsMapper = PostsMapperImpl(fakeResStorage)
        val postUiModel = PostUiModel(userId, postId, title, body)
        val user = User(name)
        val comments = mutableListOf(Comment())

        //when
        postsMapper.mapPostDetail(postUiModel, user, comments)

        //then
        verify(fakeResStorage, times(1)).getString(R.string.posts_number_comments, 1)
    }
}

