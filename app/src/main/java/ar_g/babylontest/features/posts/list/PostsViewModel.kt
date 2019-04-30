package ar_g.babylontest.features.posts.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.PostsOperationsImpl
import ar_g.babylontest.shared.Lce
import ar_g.babylontest.shared.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class PostsViewModel(
    private val postsOperations: PostsOperations,
    private val schedulersProvider: SchedulersProvider
): ViewModel() {

    private val _postsUiModels =
        MutableLiveData<Lce<List<PostUiModel>>>()
    val postsUiModels: LiveData<Lce<List<PostUiModel>>>
        get() = _postsUiModels

    private var disposable: Disposable? = null

    fun getPosts() {
        if (_postsUiModels.value == null) {
            disposable = postsOperations.getPosts()
                .observeOn(schedulersProvider.mainThread())
                .doOnSubscribe { _postsUiModels.value = Lce.Loading() }
                .subscribe(
                    { posts -> _postsUiModels.value = Lce.Content(posts) },
                    { throwable -> _postsUiModels.value = Lce.Error(throwable.message ?: "") }
                )
        }
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}