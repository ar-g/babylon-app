package ar_g.babylontest.features.posts.list

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import e.ar_g.babylontest.R

open class PostDetailsScreen: Screen<PostDetailsScreen>(){
    val title = KTextView { withId(R.id.tvTitle) }
    val body = KTextView { withId(R.id.tvBody) }
}