package ar_g.babylontest.shared.di

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import ar_g.babylontest.features.posts.PostsMapperImpl
import ar_g.babylontest.features.posts.PostsOperations
import ar_g.babylontest.features.posts.PostsOperationsImpl
import ar_g.babylontest.shared.rx.AppSchedulerProvider
import ar_g.babylontest.shared.rx.SchedulersProvider
import ar_g.babylontest.shared.api.Api
import ar_g.babylontest.shared.res.ResStorageImpl

interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null

        fun instance(context: Context): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = AppServiceLocator(context.applicationContext as Application)
                }
                return instance!!
            }
        }

        @VisibleForTesting
        fun swap(locator: ServiceLocator) {
            instance = locator
        }
    }

    fun postsOperations(): PostsOperations

    fun schedulersProvider(): SchedulersProvider
}

class AppServiceLocator(private val appContext: Context) : ServiceLocator {
    private val api by lazy {
        Api.create()
    }

    private val schedulersProvider by lazy {
        AppSchedulerProvider()
    }

    private val postsOperations by lazy {
        PostsOperationsImpl(api, PostsMapperImpl(ResStorageImpl(appContext)), schedulersProvider())
    }

    override fun postsOperations() = postsOperations

    override fun schedulersProvider() = schedulersProvider
}
