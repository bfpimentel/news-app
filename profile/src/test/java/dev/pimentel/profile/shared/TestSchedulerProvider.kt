package dev.pimentel.profile.shared

import dev.pimentel.core.schedulerprovider.SchedulerProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.TestScheduler

internal class TestSchedulerProvider(
    testScheduler: TestScheduler
) : SchedulerProvider {

    override val ui: Scheduler = testScheduler

    override val io: Scheduler = testScheduler
}
