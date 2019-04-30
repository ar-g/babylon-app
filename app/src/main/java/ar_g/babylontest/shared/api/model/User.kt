package ar_g.babylontest.shared.api.model

import com.google.gson.annotations.SerializedName

data class User(
	@field:SerializedName("name")
	val name: String
)