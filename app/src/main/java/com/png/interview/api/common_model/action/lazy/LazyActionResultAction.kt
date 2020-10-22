package com.png.interview.api.common_model.action.lazy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.png.interview.extensions.lazyCoroutine
import com.png.interview.api.common_model.action.ActionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LazyActionResultAction<T : Any>
constructor(
    private val coroutineScope: CoroutineScope,
    private val delegate: suspend () -> ActionResult<T>
) {

    private var lazyResult: Deferred<ActionResult<T>> = refresh()

    suspend fun getActionResult(): ActionResult<T> {
        if (lazyResult.await() !is ActionResult.ActionResolved) {
            this.lazyResult = refresh()
        }
        return lazyResult.await()
    }

    private fun refresh() =
        coroutineScope.lazyCoroutine {
            withContext(Dispatchers.IO) {
                delegate()
            }
        }
}

fun <T : Any> ViewModel.lazyAction(delegate: suspend () -> ActionResult<T>) = LazyActionResultAction(viewModelScope, delegate)::getActionResult