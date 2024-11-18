package com.example.cupcakeyt.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FormatedTotal(subtotal : String, modifier: Modifier){
	Text(text = "Subtotal : ${subtotal}", modifier = modifier, fontWeight = FontWeight.Bold)
}