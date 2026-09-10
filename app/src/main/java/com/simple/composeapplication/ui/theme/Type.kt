package com.simple.composeapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
private val DefaultTypography = Typography()

val AppTypography = Typography(
    headlineLarge = DefaultTypography.headlineLarge.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold, lineHeight = 40.sp),
    headlineMedium = DefaultTypography.headlineMedium.copy(fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 36.sp),
    headlineSmall = DefaultTypography.headlineSmall.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold, lineHeight = 32.sp),
    titleLarge = DefaultTypography.titleLarge.copy(fontSize = 22.sp, fontWeight = FontWeight.Bold, lineHeight = 28.sp),
    titleMedium = DefaultTypography.titleMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp),
    titleSmall = DefaultTypography.titleSmall.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold, lineHeight = 20.sp),
    bodyLarge = DefaultTypography.bodyLarge.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp),
    bodyMedium = DefaultTypography.bodyMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 20.sp),
    bodySmall = DefaultTypography.bodySmall.copy(fontSize = 12.sp, fontWeight = FontWeight.Normal, lineHeight = 16.sp),
    labelLarge = DefaultTypography.labelLarge.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 20.sp),
    labelMedium = DefaultTypography.labelMedium.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium, lineHeight = 16.sp),
    labelSmall = DefaultTypography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Medium, lineHeight = 16.sp)
)