package com.png.interview.ui.alerts.dialog

import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.png.interview.R
import com.png.interview.databinding.DialogAlertBinding
import com.png.interview.extensions.getParcelable
import com.png.interview.extensions.putParcelable
import com.png.interview.ui.InjectedDialogFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AlertDialogHTMLFragment : InjectedDialogFragment() {

    @Inject lateinit var dialogViewBinder: AlertDialogViewBinder

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DialogAlertBinding.inflate(inflater, container, false)
        binding.viewBinder = dialogViewBinder
        binding.lifecycleOwner = viewLifecycleOwner
        dialogViewBinder.bind(getParcelable())
        isCancelable = getParcelable<AlertDialogData>().isCancelable
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogViewBinder.onDismissed()
    }

    companion object {
        fun newInstance(alertDialogData: AlertDialogData): DialogFragment = AlertDialogHTMLFragment()
            .putParcelable(alertDialogData) as DialogFragment
    }

    @Parcelize
    data class AlertDialogData(
        val title: String? = null,
        val content: String? = null,
        val neutralButtonText: String? = null,
        val positiveButtonText: String? = null,
        val negativeButtonText: String? = null,
        val severity: AlertSeverity = AlertSeverity.ERROR,
        val alertDialogListener: AlertDialogListener = object :
            AlertDialogListener {},
        val isCancelable: Boolean = true,
        val isFooterVisible: Boolean = false,
        val isButtonsHorizontal: Boolean = false,
        val isContentStartAligned: Boolean = false,
        val isShowContactSupport: Boolean = false,
        val customContactSupportText: String? = null
    ) : Parcelable

    data class SimpleAlertDialogData(
        val title: Int? = null,
        val content: Int? = null,
        val neutralButtonText: Int? = null,
        val positiveButtonText: Int? = null,
        val negativeButtonText: Int? = null,
        val severity: AlertSeverity = AlertSeverity.ERROR,
        val isCancelable: Boolean = true,
        val isFooterVisible: Boolean = false,
        val isButtonsHorizontal: Boolean = false,
        val isContentStartAligned: Boolean = false,
        val positiveButtonAction: () -> Unit = {},
        val negativeButtonAction: () -> Unit = {}
    ) {
        fun toDialogData(resources: Resources) =
            AlertDialogData(
                title?.let { resources.getString(it) },
                content?.let { resources.getString(it) },
                neutralButtonText?.let { resources.getString(it) },
                positiveButtonText?.let { resources.getString(it) },
                negativeButtonText?.let { resources.getString(it) },
                severity,
                object : AlertDialogListener {
                    override fun positiveButtonClicked() {
                        positiveButtonAction()
                    }

                    override fun negativeButtonClicked() {
                        negativeButtonAction()
                    }
                },
                isCancelable,
                isFooterVisible,
                isButtonsHorizontal,
                isContentStartAligned = isContentStartAligned
            )
    }
}

@Parcelize
enum class AlertSeverity(val imageResource: Int, val iconTintResource: Int) : Parcelable {
    ERROR(R.drawable.ic_error_24dp, R.color.red),
    WARNING(R.drawable.ic_error_24dp, R.color.orange),
    INFO(R.drawable.ic_error_24dp, R.color.green)
}

interface AlertDialogListener : Parcelable {
    fun positiveButtonClicked() { /* Optional function */
    }

    fun negativeButtonClicked() { /* Optional function */
    }

    fun neutralButtonClicked() { /* Optional function */
    }

    fun onDismissed() { /* Optional function */
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) { /* Optional function */
    }
}