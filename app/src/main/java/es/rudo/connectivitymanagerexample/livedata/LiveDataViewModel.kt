package es.rudo.connectivitymanagerexample.livedata

import android.content.Context
import androidx.lifecycle.ViewModel

class LiveDataViewModel(context: Context) : LiveDataViewModelInterface, ViewModel() {
    private var _hasInternet = LiveDataNetworkManager(context)
    override val hasInternet: LiveDataNetworkManager = _hasInternet
}