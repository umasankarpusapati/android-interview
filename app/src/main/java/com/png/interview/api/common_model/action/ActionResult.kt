package com.png.interview.api.common_model.action

sealed class ActionResult<T> where T : Any? {
    data class ActionResolved<T : Any?>(val result: T) : ActionResult<T>()
    data class ActionFailed<T : Any>(val type: ActionFailedType) : ActionResult<T>()
}
