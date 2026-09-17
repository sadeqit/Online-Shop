package io.github.sadeghi.online_shop.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
import kotlin.time.Duration.Companion.milliseconds


@HiltViewModel
class SplashViewModel @Inject constructor(

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

            delay(3000.milliseconds)

            if (!isNetworkAvailable(context)) {
                _splashState.value = SplashState.NoInternet
                return@launch
            }

            _splashState.value = SplashState.InternetConnected

            delay(1200.milliseconds)

            val isLoggedIn = authRepository.isUserLoggedIn().first()


            _splashState.value =
                if (isLoggedIn)
                    SplashState.NavigateToHome
                else
                    SplashState.NavigateToAuth
        }
    }

    fun retry() {
        _splashState.value = SplashState.Loading
        checkAppState()
    }


}