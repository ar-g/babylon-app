package ar_g.babylontest.features.posts.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*

class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(postUiModel: com.example.posts_sdk.domain.response.PostUiModel) {
        itemView.tvTitle.text = postUiModel.title
    }
}