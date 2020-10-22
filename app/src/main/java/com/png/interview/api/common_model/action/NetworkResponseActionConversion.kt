@file:Suppress("MatchingDeclarationName")

package com.png.interview.api.common_model.action

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.extensions.exhaustive
import com.png.interview.api.common_model.action.ActionResult.ActionResolved

suspend fun <T : Any, W : Any, R : Any> NetworkResponse<T, W>.toActionResult(
    resultConversion: suspend (T) -> R,
    handledErrorConversion: (suspend ActionResultErrorConversion<T, R>.(Int, W) -> ActionResult<R>)? = null
): ActionResult<R> = when (this) {
    is NetworkResponse.Success -> {
        ActionResolved<R>(resultConversion(this.body))
    }
    is NetworkResponse.ServerError -> {
        this.body?.let {
            handledErrorConversion?.invoke(ActionResultErrorConversion(), this.code, it).let { convertedError ->
                convertedError
            } ?: ActionResult.ActionFailed(ActionFailedType.SERVER_ERROR)
        }
            ?: ActionResult.ActionFailed(ActionFailedType.SERVER_ERROR)
    }
    is NetworkResponse.NetworkError -> {
        ActionResult.ActionFailed(ActionFailedType.NETWORK)
    }
}.exhaustive

class ActionResultErrorConversion<T : Any, R : Any> {

    fun handleError(errorModel: R) = ActionResolved(errorModel)

    fun abort() = ActionResult.ActionFailed<R>(ActionFailedType.SERVER_ERROR)
}
