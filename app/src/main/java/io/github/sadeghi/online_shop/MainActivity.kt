package io.github.sadeghi.online_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.sadeghi.online_shop.navigation.NavGraph
import io.github.sadeghi.online_shop.ui.theme.OnlineShopTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineShopTheme {
                NavGraph()
            }
        }
    }
}

