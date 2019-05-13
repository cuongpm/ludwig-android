package com.ludwig.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class StubbedSchedulers(
    override val computation: Scheduler = Schedulers.trampoline(),
    override val io: Scheduler = Schedulers.trampoline(),
    override val newThread: Scheduler = Schedulers.trampoline(),
    override val single: Scheduler = Schedulers.trampoline(),
    override val mainThread: Scheduler = Schedulers.trampoline()
) : BaseSchedulers
