package ar_g.babylontest.features.posts.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import e.ar_g.babylontest.R
import ar_g.babylontest.features.posts.detail.PostDetailActivity
import ar_g.babylontest.shared.ui.Lce
import ar_g.babylontest.shared.di.ServiceLocator
import kotlinx.android.synthetic.main.posts_activity.*

class PostsActivity : AppCompatActivity() {

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posts_activity)

        postsAdapter = PostsAdapter { postUiModel ->
            val intent = Intent(this, PostDetailActivity::class.java)
                .putExtra(PostDetailActivity.BUNDLE_KEY, postUiModel)
            startActivity(intent)
        }
        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = postsAdapter
        srl.isEnabled = false

        val serviceLocator = ServiceLocator.instance(applicationContext)
        val factory = PostsViewModelFactory(serviceLocator.postsOperations(), serviceLocator.schedulersProvider())
        val postsViewModel: PostsViewModel = ViewModelProviders.of(this, factory).get(
            PostsViewModel::class.java)

        postsViewModel.postsUiModels.observe(this, Observer { lce ->
            when(lce){
                is Lce.Loading -> {
                    srl.isRefreshing = true
                }
                is Lce.Content -> {
                    srl.isRefreshing = false
                    postsAdapter.setData(lce.data)
                }
                is Lce.Error -> {
                    srl.isRefreshing = false
                    Toast.makeText(this, lce.errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        })
        postsViewModel.getPosts()
    }
}


