package ar_g.babylontest.features.posts.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.ui.Lce
import ar_g.babylontest.shared.rx.SchedulersProvider
import io.reactivex.disposables.Disposable

class PostDetailViewModel(
    private val postsOperations: PostsOperations,
    private val schedulersProvider: SchedulersProvider
): ViewModel() {

    private val _postDetailUiModel =
        MutableLiveData<Lce<PostDetailUiModel>>()
    val postDetailUiModel: LiveData<Lce<PostDetailUiModel>>
        get() = _postDetailUiModel

    private var disposable: Disposable? = null

    fun getPostDetail(postUiModel: PostUiModel) {
        if (_postDetailUiModel.value == null) {
            disposable = postsOperations.getPostDetail(postUiModel)
                .observeOn(schedulersProvider.mainThread())
                .doOnSubscribe {
                    _postDetailUiModel.value = Lce.Content(
                        PostDetailUiModel(postUiModel.title, postUiModel.body, "", "")
                    )
                    _postDetailUiModel.value = Lce.Loading()
                }
                .subscribe(
                    { postDetailUiModel -> _postDetailUiModel.value = Lce.Content(postDetailUiModel) },
                    { throwable -> _postDetailUiModel.value = Lce.Error(throwable.message ?: "") }
                )
        }
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}