package com.example.cupcakeyt.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cupcakeyt.data.CupCakeUiState
import com.example.cupcakeyt.ui.components.FormatedTotal

@Composable
fun SummeryScreen(cupCakeUiState: CupCakeUiState, onCancelIsClicked: () -> Unit) {
	val context = LocalContext.current
	val orderOfCupcakes =
		if (cupCakeUiState.quantity == 1) {
			"1 cupcake"
		} else {
			"${cupCakeUiState.quantity} cupcakes"
		}
	val subject = "Cupcake order summary"
	val body = """
		Quantity : ${orderOfCupcakes}
		Flavor : ${cupCakeUiState.flavor}
		Pickup date : ${cupCakeUiState.date}
		Subtotal : ${cupCakeUiState.price}
	""".trimIndent()
	val orderSummaryList: List<Pair<String, String>> = listOf(
		Pair("Quantity", orderOfCupcakes),
		Pair("Flavor", cupCakeUiState.flavor), Pair("Pickup Date", cupCakeUiState.date)
	)


	Log.d("test", "${cupCakeUiState.date.toString()} dfdsfdsf")


	Column(modifier =  Modifier.padding(horizontal = 16.dp)) {
		orderSummaryList.forEach {
			Column(modifier = Modifier.padding(horizontal = 8.dp)) {
				Text(text = it.first.uppercase(), modifier = Modifier.padding(top = 8.dp))
				Text(text = it.second, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
				HorizontalDivider()
			}
		}
		Spacer(modifier = Modifier.height(50.dp))
		FormatedTotal(subtotal = cupCakeUiState.price.toString(), modifier = Modifier.align(Alignment.End))
		Spacer(modifier = Modifier.height(50.dp))

		//this takes entire available screen pushing the next element away
		Spacer(modifier = Modifier.weight(1f))

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Button(onClick = { sendToGmail(subject, body, context) }, modifier = Modifier.fillMaxWidth()) {
				Text(text = "Send to another app")
			}
			OutlinedButton(onClick = { onCancelIsClicked() }, modifier = Modifier.fillMaxWidth()) {
				Text(text = "Cancel")
			}
		}
	}


}

fun sendToGmail(subject: String, body: String, context: Context) {
	val intent = Intent(Intent.ACTION_SEND).apply {
		type = "*/*"
		putExtra(Intent.EXTRA_SUBJECT, subject)
		putExtra(Intent.EXTRA_TEXT, body)
	}
	val chooser = Intent.createChooser(intent, "Open with")

	context.startActivity(chooser)


}
//@Composable
//@Preview
//fun SummeryScreenPreview(){
//	SummeryScreen(cupCakeUiState = )
//}

