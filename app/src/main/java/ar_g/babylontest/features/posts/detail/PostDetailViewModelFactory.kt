package ar_g.babylontest.features.posts.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar_g.babylontest.features.posts.detail.usecase.PostDetailOperations
import ar_g.babylontest.shared.rx.SchedulersProvider

class PostDetailViewModelFactory(
    private val postDetailOperations: PostDetailOperations,
    private val schedulersProvider: SchedulersProvider
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != PostDetailViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return PostDetailViewModel(postDetailOperations, schedulersProvider) as T
    }
}