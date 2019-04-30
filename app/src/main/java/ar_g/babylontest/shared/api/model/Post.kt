package ar_g.babylontest.shared.api.model

import com.google.gson.annotations.SerializedName

data class Post(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("userId")
	val userId: Int
)