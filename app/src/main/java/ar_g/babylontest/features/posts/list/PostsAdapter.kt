package ar_g.babylontest.features.posts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import e.ar_g.babylontest.R
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(private val itemClick: (com.example.posts_sdk.domain.response.PostUiModel) -> Unit) :
    RecyclerView.Adapter<PostsViewHolder>() {

    private val data = mutableListOf<com.example.posts_sdk.domain.response.PostUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        val postsViewHolder = PostsViewHolder(view)

        postsViewHolder.itemView.tvTitle.setOnClickListener {
            val position = postsViewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClick(data[position])
            }
        }

        return postsViewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(data: List<com.example.posts_sdk.domain.response.PostUiModel>) {
        this.data.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}