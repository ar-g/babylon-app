package ar_g.babylontest.shared.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider: SchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}