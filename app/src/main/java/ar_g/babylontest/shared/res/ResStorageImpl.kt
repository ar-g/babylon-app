package ar_g.babylontest.shared.res

import android.content.Context

class ResStorageImpl(private val context: Context) : ResStorage {
    override fun getString(resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}