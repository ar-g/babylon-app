package ar_g.babylontest.features.posts.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostUiModel(val userId: Int, val postId: Int, val title: String, val body: String) : Parcelable