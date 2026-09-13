package io.github.sadeghi.online_shop.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onImeAction: () -> Unit = {},
    isError: Boolean = false,
    supportingText: String? = null,
    readOnly: Boolean = false,
) {
    val borderColor = if (isError) Color.Red else Color.Transparent
    val shape = RoundedCornerShape(12.dp)

    Column(modifier = modifier.fillMaxWidth()) {

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = { onImeAction() },
                onNext = { onImeAction() }
            ),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            visualTransformation = visualTransformation,
            readOnly = readOnly,
            isError = isError,
            shape = shape,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = shape
                ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Black,

                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White,

                cursorColor = Color.Black,
                errorCursorColor = Color.Black,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,

                focusedPlaceholderColor = Color(0xFFA1A0A0),
                unfocusedPlaceholderColor = Color(0xFFA1A0A0),
                disabledPlaceholderColor = Color(0xFFA1A0A0),
                errorPlaceholderColor = Color(0xFFA1A0A0),
            )
        )

        if (!supportingText.isNullOrBlank()) {
            Text(
                text = supportingText,
                color = Color.Red,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                modifier = Modifier
                    .padding(start = 12.dp, top = 6.dp)
            )
        }
    }
}