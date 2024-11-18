package com.example.cupcakeyt.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcakeyt.R

@Composable
fun StartOrderScreen(quantityOption : List<Pair<String,Int>>,  onSelectQuantity : (Int) -> Unit,){
	Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(vertical = 16.dp, horizontal = 8.dp)) {
		Image(
			painter = painterResource(R.drawable.cupcake),
			contentDescription = null
		)
		Spacer(modifier = Modifier.height(50.dp))
		Text(text = "Order cupcakes", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 32.sp
		)
		Spacer(modifier = Modifier.weight(1f))

		Column(verticalArrangement = Arrangement.Center){
			quantityOption.forEach {
				Button( modifier = Modifier.fillMaxWidth(), onClick = { onSelectQuantity(it.second)
				//

				}) {
					Text(text = "${it.first}, ${it.second}", modifier = Modifier.fillMaxWidth().wrapContentSize())
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


