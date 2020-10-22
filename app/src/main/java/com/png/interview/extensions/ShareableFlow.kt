package com.png.interview.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class ShareableFlow<T>(flow: Flow<T>, coroutineScope: CoroutineScope) {

    private val channel = ConflatedBroadcastChannel<T>()

    init {
        coroutineScope.launch {
            flow
                .collect {
                    channel.offer(it)
                }
        }
    }

    fun getSharedFlow() = channel.asFlow()
}