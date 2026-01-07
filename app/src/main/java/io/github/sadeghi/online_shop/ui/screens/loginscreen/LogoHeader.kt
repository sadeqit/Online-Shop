package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.R

@Composable
fun LogoHeader() {
    Image(
        painter = painterResource(R.drawable.sadegh),
        contentDescription = null,
          contentScale = ContentScale.Fit,
        modifier = Modifier.size(150.dp)
    )

    Text(
        text = buildAnnotatedString {
            append("فروشگاه اینترنتی ") // متن معمولی
            withStyle(style = SpanStyle(color = Color(0xFFEF472C))) { // متن قرمز
                append("آنلاین شاپ")
            }
        },
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(Modifier.height(100.dp))
}
