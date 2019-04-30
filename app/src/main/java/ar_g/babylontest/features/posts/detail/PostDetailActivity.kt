package ar_g.babylontest.features.posts.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import e.ar_g.babylontest.R
import ar_g.babylontest.features.posts.list.PostUiModel
import ar_g.babylontest.shared.Lce
import ar_g.babylontest.shared.di.ServiceLocator
import kotlinx.android.synthetic.main.activity_post_detail.*
import java.lang.IllegalArgumentException

class PostDetailActivity : AppCompatActivity() {
    companion object {
        const val BUNDLE_KEY = "PostDetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        srl.isEnabled = false

        val serviceLocator = ServiceLocator.instance(applicationContext)

        val factory = PostDetailViewModelFactory(serviceLocator.postsOperations(), serviceLocator.schedulersProvider())
        val postDetailViewModel = ViewModelProviders.of(this, factory).get(PostDetailViewModel::class.java)

        val postUiModel = intent.getParcelableExtra<PostUiModel>(BUNDLE_KEY)
        if (postUiModel != null) {
            postDetailViewModel.postDetailUiModel.observe(this, Observer { lce ->
                when (lce) {
                    is Lce.Loading -> {
                        srl.isRefreshing = true
                    }
                    is Lce.Content -> {
                        srl.isRefreshing = false
                        tvTitle.text = lce.data.title
                        tvBody.text = lce.data.body
                        tvAuthor.text = lce.data.author
                        tvComments.text = lce.data.formattedNumberOfComments
                    }
                    is Lce.Error -> {
                        srl.isRefreshing = false
                        Toast.makeText(this, lce.errorMsg, Toast.LENGTH_LONG).show()
                    }

                }
            })
            postDetailViewModel.getPostDetail(postUiModel)
        } else {
            throw IllegalArgumentException("No PostUiModel was found by key $BUNDLE_KEY")
        }
    }
}
