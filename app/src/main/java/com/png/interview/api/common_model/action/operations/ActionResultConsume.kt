package com.png.interview.api.common_model.action.operations

import androidx.appcompat.app.AppCompatActivity
import com.png.interview.R
import com.png.interview.api.common_model.action.ActionFailedType
import com.png.interview.api.common_model.action.ActionResult
import com.png.interview.ui.alerts.AlertBuilder

suspend fun <T : Any> ActionResult<T>.onSuccess(successMapper: suspend (T) -> Unit): ActionResult<T> = this.apply {
    when (this) {
        is ActionResult.ActionResolved -> successMapper.invoke(this.result)
    }
}

suspend fun <T : Any> ActionResult<T>.takeAction(
    resolver: suspend (T) -> Unit,
    errorHandler: suspend (ActionFailedType) -> Unit
) {
    when (this) {
        is ActionResult.ActionResolved -> resolver(this.result)
        is ActionResult.ActionFailed -> errorHandler(this.type)
    }
}

suspend fun <T : Any> ActionResult<T>.takeSuccessfulAction(
    resolver: suspend (T) -> Unit
) = takeAction(resolver, {})

suspend fun <T : Any> ActionResult<T>.takeDefaultAction(
    activity: AppCompatActivity,
    resolver: suspend (T) -> Unit,
    onDismiss: () -> Unit = {}
) = takeAction(
    resolver,
    errorHandler = { failureType ->
        when (failureType) {
            ActionFailedType.NETWORK -> {
                AlertBuilder(activity).showGenericErrorAlertDialog(
                    title = activity.getString(R.string.network_error),
                    content = activity.getString(R.string.network_error_retry),
                    dismissAction = onDismiss
                )
            }
            else -> {
                AlertBuilder(activity).showUnknownErrorAlertDialog(
                    dismissAction = onDismiss
                )
            }
        }
    }
)

fun <T : Any> ActionResult<T>.takeOrNull() = if (this is ActionResult.ActionResolved) {
    this.result
} else {
    null
}