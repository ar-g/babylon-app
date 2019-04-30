package ar_g.babylontest.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Assert.fail

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.assertValues(vararg expectedRaw: T, pushValues: () -> Unit) {
    // Arrays do not print prettily, so convert them to a list
    val expectedValues = List(expectedRaw.size) { expectedRaw[it] }

    val actualValues = mutableListOf<T>()

    this.observeForever(Observer<T> {
        it ?: return@Observer
        actualValues += it
    })

    pushValues()

    if (actualValues.size > expectedValues.size) fail("LiveData emitted more values than expected\nExpected: $expectedValues\nActual  : $actualValues")
    if (actualValues.size < expectedValues.size) fail("LiveData emitted fewer values than expected\nExpected: $expectedValues\nActual  : $actualValues")

    expectedValues.zip(actualValues).forEachIndexed { i, (expect, actual) ->
        if (expect != actual) fail("Values emitted at index $i do not match\nExpected: $expectedValues\nActual  : $actualValues")
    }
}
