package com.png.interview.api.common_model.action.errors

import android.content.res.Resources
import com.png.interview.R

class ActionResultErrorUiPresenter(
    resources: Resources,
    errorType: ErrorType,
    val retryAction: () -> Unit
) {

    val hasRetry = errorType == ErrorType.OTHER

    val errorIcon = if (errorType == ErrorType.NETWORK) {
        R.drawable.network_error_icon
    } else {
        R.drawable.ic_error_cross
    }

    val errorMessage = resources.getString(
        if (errorType == ErrorType.NETWORK) {
            R.string.network_error_retry
        } else {
            R.string.unexpected_error_occurred_body
        }
    )

    fun retry() {
        retryAction()
    }

    enum class ErrorType {
        NETWORK, OTHER
    }
}