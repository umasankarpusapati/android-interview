package com.png.interview.api.common_model.action.operations

import com.png.interview.api.common_model.action.ActionResult

suspend fun <T : Any, R : Any> ActionResult<T>.flatmapResult(resolver: suspend (T) -> ActionResult<R>): ActionResult<R> =
    when (this) {
        is ActionResult.ActionResolved -> resolver(this.result)
        is ActionResult.ActionFailed -> ActionResult.ActionFailed(
            this.type
        )
    }

suspend fun <T : Any, R : Any> ActionResult<T>.mapResult(resolver: suspend (T) -> R): ActionResult<R> =
    when (this) {
        is ActionResult.ActionResolved -> ActionResult.ActionResolved(resolver(this.result))
        is ActionResult.ActionFailed -> ActionResult.ActionFailed(
            this.type
        )
    }