package ar_g.babylontest.features.posts.list

import ar_g.babylontest.shared.ui.Lce
import com.babylon.orbit.OrbitViewModel
import com.example.posts_sdk.domain.response.PostUiModel

class PostsViewModel(postsMiddleware: PostsMiddleware)
    : OrbitViewModel<Lce<List<PostUiModel>>, Any>(postsMiddleware)