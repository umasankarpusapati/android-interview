package com.png.interview.api.common_model.action.lazy

import com.png.interview.extensions.lazyCoroutine
import com.png.interview.api.common_model.action.ActionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LazyActionResultDistinctAction<T : Any, W : Any>
constructor(
    private val coroutineScope: CoroutineScope,
    private val delegate: suspend (W) -> ActionResult<T>
) {

    private var lazyResult: Deferred<ActionResult<T>>? = null
    private var cachedDependency: W? = null

    suspend fun getActionResult(dependency: W): ActionResult<T> {
        val result = lazyResult
        if (result == null || result.await() !is ActionResult.ActionResolved) {
            this.lazyResult = refresh(dependency)
        }
        return requireNotNull(lazyResult).await()
    }

    private fun refresh(dependency: W) =
        coroutineScope.lazyCoroutine {
            withContext(Dispatchers.IO) {
                delegate(dependency)
            }
        }
}

fun <W : Any, R : Any> CoroutineScope.lazyDistinctAction(delegate: suspend (W) -> ActionResult<R>) = LazyActionResultDistinctAction(this, delegate)::getActionResult