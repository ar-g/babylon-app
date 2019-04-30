package ar_g.babylontest.shared

import ar_g.babylontest.shared.rx.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ImmediateSchedulersProvider: SchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun mainThread(): Scheduler {
        return Schedulers.trampoline()
    }
}