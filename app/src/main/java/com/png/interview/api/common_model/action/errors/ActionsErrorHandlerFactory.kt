package com.png.interview.api.common_model.action.errors

import android.content.Context
import android.content.res.Resources
import androidx.databinding.ObservableField
import com.png.interview.App
import com.png.interview.api.common_model.action.NetworkChangeHelper

import com.png.interview.extensions.takeFirst
import com.png.interview.api.common_model.action.ActionFailedType
import com.png.interview.api.common_model.action.ActionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.reflect.KProperty0

@ExperimentalCoroutinesApi
class ActionsErrorHandlerFactory
@Inject constructor(
    private val networkChangeHelper: NetworkChangeHelper,
    private val resources: Resources
) {

    suspend fun createHandler(actionResult: ActionResult.ActionFailed<*>, retryAction: () -> Unit): ActionResultErrorUiPresenter? {
        var errorType = ActionResultErrorUiPresenter.ErrorType.OTHER
        if (actionResult.type == ActionFailedType.NETWORK) {
            errorType = ActionResultErrorUiPresenter.ErrorType.NETWORK
            CoroutineScope(coroutineContext).launch {
                retryOnNetworkConnection(retryAction)
            }
        }
        return ActionResultErrorUiPresenter(resources, errorType, retryAction)
    }

    private suspend fun retryOnNetworkConnection(retryAction: () -> Unit) {
        networkChangeHelper
            .getNetworkChangeFlow()
            .filter { it != NetworkChangeHelper.NetworkConnectionType.NONE }
            .takeFirst()
            .collect {
                retryAction()
            }
    }
}

@ExperimentalCoroutinesApi
@FlowPreview
suspend fun <T : Any> ActionResult<T>.bindToObservable(
    context: Context,
    observable: KProperty0<ObservableField<ActionResultErrorUiPresenter>>,
    retryAction: () -> Unit
) = this.apply {
    observable.get().set(
        (this as? ActionResult.ActionFailed)?.let { failed ->
            App.get(context).appComponent.provideErrorPresenter().createHandler(failed, retryAction)
        }
    )
}