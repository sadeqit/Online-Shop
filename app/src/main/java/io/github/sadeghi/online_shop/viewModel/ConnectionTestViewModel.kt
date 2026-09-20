package io.github.sadeghi.online_shop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectionTestViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _result = MutableStateFlow("در حال تست...")
    val result: StateFlow<String> = _result

    fun testConnection() {
        viewModelScope.launch {
            try {
                supabaseClient.auth.currentSessionOrNull()
                _result.value = "اتصال به Supabase موفق بود ✅"
            } catch (e: Exception) {
                _result.value = "خطا در اتصال: ${e.message}"
            }
        }
    }
}