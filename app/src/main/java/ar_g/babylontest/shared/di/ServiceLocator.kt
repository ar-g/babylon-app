package ar_g.babylontest.shared.di

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import ar_g.babylontest.features.posts.detail.mapper.PostDetailMapperImpl
import ar_g.babylontest.features.posts.detail.usecase.PostDetailOperations
import ar_g.babylontest.features.posts.detail.usecase.PostDetailOperationsImpl
import ar_g.babylontest.features.posts.list.PostsMiddleware
import ar_g.babylontest.shared.rx.AppSchedulerProvider
import ar_g.babylontest.shared.rx.SchedulersProvider
import ar_g.babylontest.shared.api.Api
import ar_g.babylontest.shared.res.ResStorageImpl
import com.example.posts_sdk.PostsApi
import com.example.posts_sdk.PostsSdk

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

    fun postsOperations(): PostDetailOperations

    fun schedulersProvider(): SchedulersProvider

    fun postsApi(): PostsApi

    fun postsMiddleware() : PostsMiddleware
}

class AppServiceLocator(private val appContext: Context) : ServiceLocator {

    private val api by lazy {
        Api.create()
    }

    private val schedulersProvider by lazy {
        AppSchedulerProvider()
    }

    private val postsOperations by lazy {
        PostDetailOperationsImpl(
            api,
            PostDetailMapperImpl(ResStorageImpl(appContext)), schedulersProvider()
        )
    }

    override fun postsOperations() = postsOperations

    override fun schedulersProvider() = schedulersProvider

    override fun postsApi() = PostsSdk.apiInstance

    override fun postsMiddleware() = PostsMiddleware(postsApi())
}
