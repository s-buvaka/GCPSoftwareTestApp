package com.wispapp.gcpsoftwaretestapp.core.executors

import java.util.concurrent.Executor

data class AppExecutors(
    val background: Executor,
    val mainThread: Executor
)