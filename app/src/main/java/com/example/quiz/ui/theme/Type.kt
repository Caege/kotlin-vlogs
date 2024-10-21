package com.example.quiz.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quiz.R

//val AppTypography = Typography()


val abrilFatFace = FontFamily(
	Font(R.font.abrilfatface_regular)
)

val montserrat = FontFamily(
	Font(R.font.abrilfatface_regular),
	Font(R.font.montserrat_bold, FontWeight.Bold)
)


val AppTypography = Typography(
	displayLarge = TextStyle(
		fontFamily = abrilFatFace,
		fontSize = 36.sp,
		fontWeight = FontWeight.Normal
	),
	displayMedium = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Bold,
		fontSize = 20.sp
	),
	labelSmall = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Bold,
		fontSize = 14.sp
	),
	bodyLarge = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Normal,
		fontSize = 14.sp
	)

)