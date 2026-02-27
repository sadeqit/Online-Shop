package io.github.sadeghi.online_shop.viewModel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.sadeghi.online_shop.data.repository.AuthRepository
import io.github.sadeghi.online_shop.data.repository.IAuthRepository
import io.github.sadeghi.online_shop.ui.ui_utils.SplashState
import io.github.sadeghi.online_shop.utils.isNetworkAvailable
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    /*private val application: Application,
    private val authRepository: AuthRepository*/
    private val context: Application,
    private val authRepository: IAuthRepository
) : ViewModel() {

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Loading)
    val splashState: StateFlow<SplashState> = _splashState

    private var splashJob: Job? = null

    init {
        checkAppState()
    }

    private fun checkAppState() {

        splashJob?.cancel()

        splashJob = viewModelScope.launch {

            delay(3000)

            if (!isNetworkAvailable(context)) {
                _splashState.value = SplashState.NoInternet
                return@launch
            }

            _splashState.value = SplashState.InternetConnected

            delay(1200)

            val isLoggedIn = authRepository.isUserLoggedIn().first()

            _splashState.value =
                if (isLoggedIn)
                    SplashState.NavigateToHome
                else
                    SplashState.NavigateToAuth
        }
    }
    /*private fun checkAppState() {

        splashJob?.cancel()

        viewModelScope.launch {

            delay(5000) // مدت زمان انیمیشن اسپلش

            if (!isNetworkAvailable(context)) {
                _splashState.value = SplashState.NoInternet
                return@launch
            }

            _splashState.value = SplashState.InternetConnected

            delay(1200) // زمان نمایش متن سبز

            val isLoggedIn = authRepository.isUserLoggedIn().first()

            _splashState.value =
                if (isLoggedIn)
                    SplashState.NavigateToHome
                else
                    SplashState.NavigateToAuth
        }
    }*/

    fun retry() {
        _splashState.value = SplashState.Loading
        checkAppState()
    }


    /*
        var isLoggedIn by mutableStateOf<Boolean?>(null)
            private set

        private val _isConnected = MutableStateFlow<Boolean?>(null)
        val isConnected : StateFlow<Boolean?> = _isConnected

        init {
            checkInternet()
            viewModelScope.launch {
                authRepository.isUserLoggedIn().collect {
                    isLoggedIn = it
                }
            }

        }
        fun checkInternet(){
            _isConnected.value=null
            viewModelScope.launch {
                delay(3000)
                _isConnected.value = isNetworkAvailable(application)

            }
        }*/

}