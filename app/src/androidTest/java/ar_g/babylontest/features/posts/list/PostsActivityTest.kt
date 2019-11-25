package ar_g.babylontest.features.posts.list


import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import ar_g.babylontest.features.posts.list.PostsActivityScreen.PostItem
import com.agoda.kakao.screen.Screen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class PostsActivityTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(PostsActivity::class.java)

    @Test
    fun postsAreCorrect() {
        Screen.idle(1000)

        Screen.onScreen<PostsActivityScreen> {
            recycler {
                isVisible()
                hasSize(100)

                firstChild<PostItem> {
                    isVisible()
                    postTitle.hasText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                }
            }
        }
    }


    @Test
    fun postDetailsAreCorrect() {
        Screen.idle(1000)

        Screen.onScreen<PostsActivityScreen> {
            recycler {
                firstChild<PostItem> {
                    click()
                }
            }
        }

        Screen.onScreen<PostDetailsScreen> {
            title.hasText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
            body.containsText("quia et suscipit")
        }
    }
}

