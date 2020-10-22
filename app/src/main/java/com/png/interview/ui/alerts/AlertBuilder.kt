package com.png.interview.ui.alerts

import androidx.appcompat.app.AppCompatActivity
import com.png.interview.R
import com.png.interview.ui.alerts.dialog.AlertDialogHTMLFragment
import com.png.interview.ui.alerts.dialog.AlertDialogListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@Suppress("TooManyFunctions")
class AlertBuilder
@Inject constructor(
    private val activity: AppCompatActivity
) {
    fun showAlertDialog(alertDialogData: AlertDialogHTMLFragment.AlertDialogData) {
        AlertDialogHTMLFragment.newInstance(alertDialogData)
            .show(activity.supportFragmentManager, null)
    }

    fun showAlertDialog(alertDialogData: AlertDialogHTMLFragment.SimpleAlertDialogData) {
        AlertDialogHTMLFragment.newInstance(alertDialogData.toDialogData(activity.resources))
            .show(activity.supportFragmentManager, null)
    }

    fun showGenericErrorAlertDialog(content: Int, showContactSupport: Boolean = false) {
        showGenericErrorAlertDialog(activity.getString(content), showContactSupport)
    }

    fun showNetworkErrorAlertDialog() {
        showAlertDialog(
            AlertDialogHTMLFragment.AlertDialogData(
                title = activity.getString(R.string.network_error),
                content = activity.getString(R.string.network_error_retry),
                positiveButtonText = activity.getString(R.string.ok)
            )
        )
    }

    fun showGenericErrorAlertDialog(content: String, showContactSupport: Boolean = false) {
        showAlertDialog(
            AlertDialogHTMLFragment.AlertDialogData(
                title = activity.getString(R.string.alert_error_title),
                content = content,
                positiveButtonText = activity.getString(R.string.ok),
                isShowContactSupport = showContactSupport
            )
        )
    }

    fun showGenericErrorAlertDialog(title: Int, content: Int) {
        showGenericErrorAlertDialog(title = activity.getString(title), content = activity.getString(content))
    }

    fun showGenericErrorAlertDialog(title: String, content: String, dismissAction: () -> Unit = {}) {
        showAlertDialog(
            AlertDialogHTMLFragment.AlertDialogData(
                title = title,
                content = content,
                positiveButtonText = activity.getString(R.string.ok),
                alertDialogListener = object : AlertDialogListener {
                    override fun onDismissed() {
                        dismissAction()
                    }
                }
            )
        )
    }

    fun showUnknownErrorAlertDialog(dismissAction: () -> Unit = {}) {
        showAlertDialog(
            AlertDialogHTMLFragment.AlertDialogData(
                title = activity.getString(R.string.alert_error_title),
                content = activity.getString(R.string.please_try_again),
                positiveButtonText = activity.getString(R.string.ok),
                alertDialogListener = object : AlertDialogListener {
                    override fun onDismissed() {
                        dismissAction()
                    }
                }
            )
        )
    }
}