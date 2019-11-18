package ar_g.babylontest.features.posts.list

import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.shared.ui.Lce
import ar_g.babylontest.shared.rx.SchedulersProvider
import com.babylon.orbit.LifecycleAction
import com.babylon.orbit.OrbitViewModel

class PostsViewModel(
    private val postsOperations: PostsOperations,
    private val schedulersProvider: SchedulersProvider,

): OrbitViewModel<Lce<List<com.example.posts_sdk.domain.response.PostUiModel>>, SideEffect>(Lce.Loading(), {
    perform("onCreate")
        .on<LifecycleAction.Created>()
        .transform { postsOperations.getPosts().toObservable() }
        .reduce { Lce.Content(event) }
})


/*
*  private val _postsUiModels =
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
*
* */

data class State(val total: Int = 0)

sealed class SideEffect {
    data class Toast(val text: String) : SideEffect()
}



