package ar_g.babylontest.features.posts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.posts_sdk.PostsApi

class PostsViewModelFactory(
    private val postsApi: PostsApi
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == PostsViewModel::class.java) { "Unknown ViewModel class" }
        return PostsViewModel(postsApi) as T
    }
}