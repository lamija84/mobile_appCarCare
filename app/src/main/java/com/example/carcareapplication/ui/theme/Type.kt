package com.example.carcareapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.carcareapplication.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
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
val poppinsFontFamily = FontFamily(
    Font(R.font.poppins1extralight, FontWeight.Black),
    Font(R.font.poppinsblack, FontWeight.Black),
    Font(R.font.poppinsbold, FontWeight.Black),
    Font(R.font.poppinsbolditalic, FontWeight.Black),
    Font(R.font.poppinsextrabold, FontWeight.Black),
    Font(R.font.poppinsextralightitalic, FontWeight.Black),
    Font(R.font.poppinsitalic, FontWeight.Black),
    Font(R.font.poppinslight, FontWeight.Black),
    Font(R.font.poppinsmedium, FontWeight.Black),
    Font(R.font.poppinsmediumitalic, FontWeight.Black),
    Font(R.font.poppinsregular, FontWeight.Black),
    Font(R.font.poppinsthin, FontWeight.Black),
)