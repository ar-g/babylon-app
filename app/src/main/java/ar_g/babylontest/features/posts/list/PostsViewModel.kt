package ar_g.babylontest.features.posts.list

import ar_g.babylontest.shared.ui.Lce
import com.babylon.orbit.LifecycleAction
import com.babylon.orbit.OrbitViewModel
import com.example.posts_sdk.PostsApi
import com.example.posts_sdk.core.Status
import com.example.posts_sdk.core.asStatusObservable
import com.example.posts_sdk.domain.response.PostUiModel

class PostsViewModel(
    private val postsApi: PostsApi
): OrbitViewModel<Lce<List<PostUiModel>>, Any>(Lce.Loading(), {
    perform("onCreate")
        .on<LifecycleAction.Created>()
        .transform {
            eventObservable.switchMap {
                postsApi.getPosts().asStatusObservable()
            }
        }
        .reduce {
            when(event){
                is Status.Result -> Lce.Content((event as Status.Result<List<PostUiModel>>).data)
                is Status.Loading -> Lce.Loading()
                is Status.Failure -> Lce.Error((event as Status.Failure<List<PostUiModel>>).exception.message ?: "")
            }
        }
})