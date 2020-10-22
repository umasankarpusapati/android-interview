package com.png.interview.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

suspend fun <T> onMain(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.Main, block)

fun <T> AppCompatActivity.launchOnIo(block: () -> T) = this.lifecycleScope.launch(Dispatchers.IO) {
    block()
}

fun <T> CoroutineScope.lazyCoroutine(
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return async(start = CoroutineStart.LAZY) { block() }
}