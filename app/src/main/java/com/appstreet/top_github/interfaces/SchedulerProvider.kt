package com.appstreet.top_github.interfaces

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}