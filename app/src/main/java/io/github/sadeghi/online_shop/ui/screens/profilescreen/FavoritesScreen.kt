package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.ProductItem
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.bestsellingProducts
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.viewModel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favoriteProductIds by viewModel.favoriteProductIds
        .collectAsStateWithLifecycle()

    val favoriteProducts = bestsellingProducts.filter {
        it.id in favoriteProductIds
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HeaderProfile(true)

        SpacerHeight(50)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Text(
                text = "علاقه مندی های من",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(20)

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = favoriteProducts,
                        key = { it.id }
                    ) { product ->

                        CompositionLocalProvider(
                            LocalLayoutDirection provides LayoutDirection.Ltr
                        ) {
                            ProductItem(
                                product = product,
                                showBookmark = true,
                                onClick = {
                                    navController.navigate(
                                        Screens.ProductDetail.createRoute(product.id)
                                    )
                                }
                            )
                        }
                    }
                }
            }
    }
}}
/*@Composable
fun FavoritesScreen() {


}*/
