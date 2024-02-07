package com.example.sungdongwalk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sungdongwalk.R

val suit = FontFamily(
    Font(R.font.suit_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.suit_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.suit_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.suit_regular, FontWeight.Normal, FontStyle.Normal),
)
// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)