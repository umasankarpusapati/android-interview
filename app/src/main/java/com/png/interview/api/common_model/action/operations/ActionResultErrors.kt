package com.png.interview.api.common_model.action.operations

import com.png.interview.api.common_model.action.ActionFailedType
import com.png.interview.api.common_model.action.ActionResult

fun <T : Any> ActionResult<T>.doOnError(action: () -> Unit) =
    this.apply {
        if (this !is ActionResult.ActionResolved) {
            action()
        }
    }

fun <T : Any?> ActionResult<T>.onErrorReturn(errorMapper: (ActionFailedType) -> T): ActionResult.ActionResolved<T> =
    when (this) {
        is ActionResult.ActionResolved -> this
        is ActionResult.ActionFailed -> ActionResult.ActionResolved(
            errorMapper(this.type)
        )
    }