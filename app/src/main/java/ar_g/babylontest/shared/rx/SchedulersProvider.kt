package ar_g.babylontest.shared.rx

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io() : Scheduler
    fun mainThread() : Scheduler
}