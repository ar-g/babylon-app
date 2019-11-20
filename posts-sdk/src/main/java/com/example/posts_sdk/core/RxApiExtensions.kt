package com.example.posts_sdk.core

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.rx2.rxSingle
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * The [Observable] exposed here executes on the calling thread by default.
 * Callers should take care of setting the correct schedulers
 */
fun <T : Any> Call<T>.asStatusObservable(): Observable<Status<T>> =
    asSingle()
        .toObservable()
        .mapStatus()

/**
 * The [Single] exposed here executes on the calling thread by default.
 * Callers should take care of setting the correct schedulers
 */
fun <T : Any> Call<T>.asSingle(): Single<T> = rxSingle(Dispatchers.Unconfined + RxCoroutineExceptionHandler) {
    this@asSingle.execute()
}

/**
 * Maps an [Observable] to a [Status] with loading, failure, result.
 */
fun <T : Any> Observable<T>.mapStatus(): Observable<Status<T>> =
    this.map<Status<T>> { Status.Result(it) }
        .startWith(Status.Loading())
        .onErrorReturn { throwable -> Status.Failure(throwable) }

/**
 * This class handles undeliverable cancellation exceptions after an `rxSingle` has been disposed of.
 */
private object RxCoroutineExceptionHandler : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        if (exception is CancellationException || exception is InterruptedException) return

        Exceptions.throwIfFatal(exception)
        RxJavaPlugins.onError(exception)
    }
}
