package io.github.sadeghi.online_shop

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.github.sadeghi.online_shop.navigation.NavGraph
import io.github.sadeghi.online_shop.core.ui.BGShape
import io.github.sadeghi.online_shop.core.theme.OnlineShopTheme

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = Color.WHITE
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContent {
            OnlineShopTheme {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BGShape()
                    NavGraph()
                }
            }
        }
    }
}