package io.github.sadeghi.online_shop.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.utils.isNetworkAvailable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val app: Application
) : ViewModel() {

    private val _isConnected = MutableStateFlow<Boolean?>(null)
    val isConnected : StateFlow<Boolean?> = _isConnected

    init {
        checkInternet()
    }
    fun checkInternet(){
        _isConnected.value=null
        viewModelScope.launch {
            delay(3000)
            _isConnected.value = isNetworkAvailable(app)

        }
    }


}