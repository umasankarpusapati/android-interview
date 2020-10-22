package com.png.interview.extensions

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

@UseExperimental(ExperimentalCoroutinesApi::class)
fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 0): Flow<Nothing?> =
    flow {
        while (true) {
            emit(null)
            delay(repeatMillis)
            if (!coroutineContext.isActive) {
                break
            }
        }
    }.onStart {
        delay(delayMillis)
        emit(null)
    }