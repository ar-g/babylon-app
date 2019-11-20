package com.example.posts_sdk.core

import androidx.annotation.CheckResult

interface Call<T> {

    /**
     * Whether the call has been cancelled. This is set to true immediately after the
     * first call to [cancel]
     */
    val cancelled: Boolean

    /**
     * Whether the call has been already executed. This is set to true immediately after the
     * first call to [execute] or [enqueue]
     */
    val executed: Boolean


    /**
     * Suppress the delivery of exceptions to the global error relay available through the Core SDK.
     */
    @CheckResult
    fun suppressGlobalAuthErrors(): Call<T>

    /**
     * Cancels an in-progress call
     */
    fun cancel()

    /**
     * Executes the call synchronously, blocking the current thread until the operation completes.
     *
     * @throws [IllegalStateException] if the [Call] has already been run before
     * @throws [CancellationException] if the [Call] has been previously cancelled
     * @return the result of the [Call]
     */
    fun execute(): T

    /**
     * Executes the call asynchronously, running the operation on an IO thread. The results are
     * then delivered on the Android main thread.
     *
     * @param callback
     *
     * @throws [IllegalStateException] if the [Call] has already been run before
     * @throws [CancellationException] if the [Call] has been previously cancelled
     */
    fun enqueue(callback: Callback<T>)
}