package ar_g.babylontest.features.posts.fakes

import ar_g.babylontest.shared.res.ResStorage

open class FakeResStorage : ResStorage {
    companion object {
        const val RETURN_STRING = "1 comment"
    }

    override fun getString(resId: Int, vararg formatArgs: Any?): String {
        return RETURN_STRING
    }
}