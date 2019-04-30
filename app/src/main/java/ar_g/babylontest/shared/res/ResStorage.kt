package ar_g.babylontest.shared.res

interface ResStorage{
    fun getString(resId: Int, vararg formatArgs: Any?): String
}