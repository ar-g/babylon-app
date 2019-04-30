package ar_g.babylontest.features.posts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.PostsOperationsImpl
import ar_g.babylontest.shared.SchedulersProvider

class PostsViewModelFactory(
    private val postsOperations: PostsOperations,
    private val schedulersProvider: SchedulersProvider
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != PostsViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return PostsViewModel(postsOperations,schedulersProvider) as T
    }
}