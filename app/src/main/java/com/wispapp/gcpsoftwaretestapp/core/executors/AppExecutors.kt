package com.wispapp.gcpsoftwaretestapp.core.executors

import java.util.concurrent.Executor

data class AppExecutors(
    val backgroundExecutor: Executor = BackgroundExecutor(),
    val mainThreadExecutor: Executor = UiThreadExecutor()
)