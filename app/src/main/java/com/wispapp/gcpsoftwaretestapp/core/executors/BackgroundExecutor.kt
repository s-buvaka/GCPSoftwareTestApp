package com.wispapp.gcpsoftwaretestapp.core.executors

import java.util.concurrent.Executor
import java.util.concurrent.Executors


class BackgroundExecutor : Executor {

    private val dbExecutor = Executors.newSingleThreadExecutor()

    override fun execute(runnable: Runnable) {
        dbExecutor.execute(runnable)
    }
}