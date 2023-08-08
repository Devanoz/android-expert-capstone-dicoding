package com.example.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class AppExecutors @VisibleForTesting constructor(
    private val diskIo: Executor,
    private val networkIo: Executor,
    private val mainThread: Executor
) {
    companion object {
        private const val TRHEAD_COUNT = 3
    }

    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(TRHEAD_COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIo
    fun networkIO(): Executor = networkIo
    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}