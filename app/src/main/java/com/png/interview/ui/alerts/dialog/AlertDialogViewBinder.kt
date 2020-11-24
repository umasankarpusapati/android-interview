package com.png.interview.ui.alerts.dialog

import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AlertDialogViewBinder
@Inject constructor(
    private val dialogFragment: DialogFragment
) {
    lateinit var alertDialogData: AlertDialogHTMLFragment.AlertDialogData

    var title: String? = null
    var content: String? = null
    var neutralButtonText: String? = ""
    var positiveButtonText: String? = ""
    var negativeButtonText: String? = ""
    var neutralButtonVisible: Boolean = false
    var positiveButtonVisible: Boolean = false
    var negativeButtonVisible: Boolean = false
    var isFooterVisible: Boolean = false
    var isButtonsHorizontal: Boolean = false
    var isCancelable: Boolean = true
    var isShowContactSupport: Boolean = false
    var isContentStartAligned: Boolean = false
    var customContactSupportText: String? = null

    fun bind(data: AlertDialogHTMLFragment.AlertDialogData) {
        this.alertDialogData = data
        title = data.title
        content = data.content
        neutralButtonText = data.neutralButtonText
        positiveButtonText = data.positiveButtonText
        negativeButtonText = data.negativeButtonText
        neutralButtonVisible = !neutralButtonText.isNullOrEmpty()
        positiveButtonVisible = !positiveButtonText.isNullOrEmpty()
        negativeButtonVisible = !negativeButtonText.isNullOrEmpty()
        isFooterVisible = data.isFooterVisible
        isButtonsHorizontal = data.isButtonsHorizontal
        isCancelable = data.isCancelable
        isShowContactSupport = data.isShowContactSupport
        customContactSupportText = data.customContactSupportText
        isContentStartAligned = data.isContentStartAligned
    }

    fun neutralButtonClicked() {
        this.alertDialogData.alertDialogListener.neutralButtonClicked()
        dialogFragment.dismiss()
    }

    fun positiveButtonClicked() {
        this.alertDialogData.alertDialogListener.positiveButtonClicked()
        dialogFragment.dismissAllowingStateLoss()
    }

    fun negativeButtonClicked() {
        this.alertDialogData.alertDialogListener.negativeButtonClicked()
        dialogFragment.dismissAllowingStateLoss()
    }

    fun onDismissed() {
        this.alertDialogData.alertDialogListener.onDismissed()
        dialogFragment.dismissAllowingStateLoss()
    }

    fun closeClicked() {
        dialogFragment.dismissAllowingStateLoss()
    }
}