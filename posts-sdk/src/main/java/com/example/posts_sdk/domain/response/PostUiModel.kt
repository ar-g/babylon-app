package com.example.posts_sdk.domain.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostUiModel(val userId: Int, val postId: Int, val title: String, val body: String) : Parcelable