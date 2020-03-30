package dev.pimentel.core.schedulerprovider

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface SchedulerProvider {
    val ui: Scheduler
    val io: Scheduler
}

class SchedulerProviderImpl : SchedulerProvider {

    override val ui: Scheduler = AndroidSchedulers.mainThread()

    override val io: Scheduler = Schedulers.io()
}
