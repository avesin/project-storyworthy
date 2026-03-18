package com.jadigaknih.storyworthy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jadigaknih.storyworthy.R


@Composable
fun StoryworthyTheme(
    content: @Composable () -> Unit
) {
    val lightColors = lightColorScheme(
        background = colorResource(R.color.white),
        surface = colorResource(R.color.white),
        primary = colorResource(R.color.purple),
        onPrimary = Color.White,
        onBackground = colorResource(R.color.black),
        onSurface = colorResource(R.color.black)
    )

    val caveat = FontFamily(
        Font(R.font.patrickhand_regular, FontWeight.Normal),
        Font(R.font.caveat_bold, FontWeight.Bold)
    )

    val storyTypography = Typography(
        bodyLarge = TextStyle(
            fontFamily = caveat,
            fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = caveat,
            fontSize = 16.sp
        ),
        titleLarge = TextStyle(
            fontFamily = caveat,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )

    MaterialTheme(
        colorScheme = lightColors,
        typography = storyTypography,
        content = content,
    )
}