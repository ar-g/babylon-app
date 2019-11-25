package ar_g.babylontest.features.posts.list

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import e.ar_g.babylontest.R
import org.hamcrest.Matcher

open class PostsActivityScreen: Screen<PostsActivityScreen>() {
    val recycler =
        KRecyclerView({ withId(R.id.rvPosts) }, itemTypeBuilder = {
            itemType(::PostItem)
        })

    class PostItem(parentMatcher: Matcher<View>) : KRecyclerItem<PostItem>(parentMatcher) {
        val postTitle = KTextView { withMatcher(parentMatcher) }
    }
}