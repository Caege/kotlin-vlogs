package com.example.cupcakeyt.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcakeyt.R

@Composable
fun StartOrderScreen(quantityOption : List<Pair<String,Int>>,  onSelectQuantity : (Int) -> Unit,){
	Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
		Image(
			painter = painterResource(R.drawable.cupcake),
			contentDescription = null
		)
		Spacer(modifier = Modifier.height(50.dp))
		Text(text = "Order cupcakes")
		Spacer(modifier = Modifier.height(50.dp))

		Column(verticalArrangement = Arrangement.Center){
			quantityOption.forEach {
				Button(onClick = { onSelectQuantity(it.second)
				//
				}) {
					Text(text = "${it.first}, ${it.second}")
				}
			}
		}

	}






}

@Preview
@Composable
fun StartOrderScreenPreview(){
//	StartOrderScreen(quantityOption = DataSource.quantityOptions)
}


