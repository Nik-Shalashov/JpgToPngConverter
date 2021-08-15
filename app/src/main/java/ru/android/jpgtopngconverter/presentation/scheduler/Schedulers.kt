package ru.android.jpgtopngconverter.presentation.scheduler

import io.reactivex.Scheduler

interface Schedulers {

    fun background(): Scheduler
    fun main(): Scheduler
}