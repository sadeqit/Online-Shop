package io.github.sadeghi.online_shop.feature.auth.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.core.theme.orange

@Composable
fun LogoHeader() {

    Column(
        modifier = Modifier.padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = buildAnnotatedString {
                append("فروشگاه اینترنتی ")
                withStyle(style = SpanStyle(color = orange)) {
                    append("آنلاین شاپ")
                }
            },
            style = MaterialTheme.typography.titleLarge
        )

    }
}