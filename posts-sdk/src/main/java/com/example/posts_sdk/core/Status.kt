package com.example.posts_sdk.core

/**
 * Status object representing the execution status of a [Call] delivered through [Call.asStatusObervable]
 */
sealed class Status<T> {
    /**
     * A [Status] describing a successful outcome of the execution
     *
     * @property data The result data
     */
    data class Result<T>(val data: T) : Status<T>()

    /**
     * A [Status] describing an in progress execution
     *
     */
    class Loading<T> : Status<T>() {
        override fun equals(other: Any?): Boolean {
            return other is Loading<*> // TODO @mikolaj.leszczynski JUL-2019 find a way to check for equality that takes T into consideration
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    /**
     * A [Status] describing an unsuccessful outcome of the execution
     *
     * @property exception The cause of the failure
     */
    data class Failure<T>(val exception: Throwable) : Status<T>()
}
