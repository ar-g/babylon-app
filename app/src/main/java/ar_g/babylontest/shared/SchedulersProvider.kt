package ar_g.babylontest.shared

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io() : Scheduler
    fun mainThread() : Scheduler
}