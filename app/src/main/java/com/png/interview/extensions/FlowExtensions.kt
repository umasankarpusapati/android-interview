package com.png.interview.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.zip

@ExperimentalCoroutinesApi
fun <T> LiveData<T>.asFlow() = channelFlow<T> {
    value?.let { offer(it) }
    val observer = Observer<T> { t -> t?.let { offer(t) } }
    observeForever(observer)
    awaitClose { removeObserver(observer) }
}

fun <T> Flow<T>.takeFirst() = this.take(1)

fun <T, W> Flow<T>.zipToPair(flow: Flow<W>): Flow<Pair<T, W>> = this.zip(flow) { first, second -> first to second }

fun <T> Flow<T>.share(coroutineScope: CoroutineScope) =
    ShareableFlow(
        this, coroutineScope
    )

fun <T> Flow<T>.toLiveDataWithFragment(fragment: Fragment) = this.asLiveData(fragment.lifecycleScope.coroutineContext)

inline fun <T> broadcastChannel() = BroadcastChannel<T>(Channel.BUFFERED)

fun <T> Flow<T>.onFirst(action: suspend (T) -> Unit) = flow {
    var consumed = false
    collect {
        emit(it)
        if (!consumed) {
            action(it)
            consumed = true
        }
    }
}