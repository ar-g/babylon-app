package com.example.posts_sdk.core

import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.CancellationException

internal class WrappedCall<T : Any>(
    private val func: suspend () -> T
) : Call<T> {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    @Volatile
    private var _executed = false

    private val deferred: Deferred<T> = scope.async(Dispatchers.IO, start = CoroutineStart.LAZY) {
        try {
            func()
        } catch (e: Exception) {
            throw e
        }
    }

    private var listener: Callback<T>? = null

    override val executed: Boolean
        get() = _executed

    override val cancelled: Boolean
        get() = scope.coroutineContext[Job]?.isCancelled ?: false

    override fun suppressGlobalAuthErrors() =
        WrappedCall(
            func = func
        )

    override fun cancel() {
        listener = null
        scope.cancel()
    }

    override fun execute(): T { // this needs to respect cancellations
        ensureCanBeStarted()

        return runBlocking {
            // Not sure why deffered's scope is not being cancelled automatically within runBlocking
            val result = deferred.runCatching { await() }
            result.exceptionOrNull()?.also { scope.cancel(CancellationException("Call has been cancelled", it)) }
            result.getOrThrow()
        }
    }

    override fun enqueue(callback: Callback<T>) {
        ensureCanBeStarted()

        listener = callback

        val handler = CoroutineExceptionHandler { _, throwable ->
            listener?.onException(throwable)
        }

        scope.launch(handler) {
            val result = deferred.await()

            listener?.onResult(result)
        }
    }

    private fun ensureCanBeStarted() {
        synchronized(_executed) {
            when {
                _executed -> throw IllegalStateException("Call has already been executed. Each Call can be executed only once")
                cancelled -> throw CancellationException("Call has been cancelled. Please use a new Call object")
                else -> _executed = true
            }
        }
    }
}

fun <T : Any> wrapCall(func: suspend () -> T): Call<T> = WrappedCall(func = func)
