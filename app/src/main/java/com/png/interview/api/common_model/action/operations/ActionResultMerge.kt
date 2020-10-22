package com.png.interview.api.common_model.action.operations

import com.png.interview.api.common_model.action.ActionFailedType
import com.png.interview.api.common_model.action.ActionResult

suspend fun <T1 : Any, T2 : Any, R : Any> combineSuccessful(
    firstAction: ActionResult<T1>,
    secondAction: ActionResult<T2>,
    successfulCombine: suspend (T1, T2) -> R
): ActionResult<R> {
    return if (firstAction is ActionResult.ActionResolved && secondAction is ActionResult.ActionResolved) {
        ActionResult.ActionResolved(successfulCombine(firstAction.result, secondAction.result))
    } else {
        ActionResult.ActionFailed(
            if (listOf(firstAction, secondAction).filterIsInstance<ActionResult.ActionFailed<*>>()
                    .any { it.type == ActionFailedType.NETWORK }
            ) {
                ActionFailedType.NETWORK
            } else {
                ActionFailedType.SERVER_ERROR
            }
        )
    }
}

suspend fun <T1 : Any, T2 : Any, R : Any> combineSuccessfulAsAction(
    firstAction: ActionResult<T1>,
    secondAction: ActionResult<T2>,
    successfulCombine: suspend (T1, T2) -> ActionResult<R>
): ActionResult<R> {
    return if (firstAction is ActionResult.ActionResolved && secondAction is ActionResult.ActionResolved) {
        successfulCombine(firstAction.result, secondAction.result)
    } else {
        ActionResult.ActionFailed(
            if (listOf(firstAction, secondAction).filterIsInstance<ActionResult.ActionFailed<*>>()
                    .any { it.type == ActionFailedType.NETWORK }
            ) {
                ActionFailedType.NETWORK
            } else {
                ActionFailedType.SERVER_ERROR
            }
        )
    }
}

suspend fun <T1 : Any, T2 : Any, T3 : Any, R : Any> combineSuccessfulAsAction(
    firstAction: ActionResult<T1>,
    secondAction: ActionResult<T2>,
    thirdAction: ActionResult<T3>,
    successfulCombine: suspend (T1, T2, T3) -> ActionResult<R>
): ActionResult<R> {
    return if (firstAction is ActionResult.ActionResolved && secondAction is ActionResult.ActionResolved && thirdAction is ActionResult.ActionResolved) {
        successfulCombine(firstAction.result, secondAction.result, thirdAction.result)
    } else {
        ActionResult.ActionFailed(
            if (listOf(firstAction, secondAction, thirdAction).filterIsInstance<ActionResult.ActionFailed<*>>()
                    .any { it.type == ActionFailedType.NETWORK }
            ) {
                ActionFailedType.NETWORK
            } else {
                ActionFailedType.SERVER_ERROR
            }
        )
    }
}