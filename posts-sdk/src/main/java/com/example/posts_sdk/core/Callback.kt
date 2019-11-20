package com.example.posts_sdk.core

interface Callback<T> {
    /**
     * Delivers the result when the [Call] completes successfully
     *
     * @param data the result of the [Call]
     */
    fun onResult(data: T)

    /**
     * Called whenever the [Call] is unsuccessful.
     *
     * @param throwable the cause of the failure
     */
    fun onException(throwable: Throwable)
}