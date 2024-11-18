package com.example.cupcakeyt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcakeyt.data.DataSource
import com.example.cupcakeyt.ui.components.FormatedTotal

@Composable
fun SelectOptionsScreen(options: List<String>, subtotal: String, onSelected: (String) -> Unit, onNextButtonIsclicked: () -> Unit, onCancelButtonIsClicked: () -> Unit) {
	var selected by remember {
		mutableStateOf("")
	}
	Column(
		verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp, vertical = 20.dp)
	) {
		options.forEach {
			Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.selectable(selected = selected == it) {
				selected = it



				onSelected(it)
			}) {
				RadioButton(selected = selected == it, onClick = {
					selected = it

					onSelected(it)
				})

				Text(text = it)
			}



			HorizontalDivider()
		}



		FormatedTotal(subtotal = subtotal, modifier = Modifier.align(Alignment.End).padding(top = 16.dp))


		Spacer(modifier = Modifier.weight(1f))

		Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			OutlinedButton(onClick = { onCancelButtonIsClicked() }, modifier = Modifier.weight(1f)) {
				Text("Cancel")
			}
			Button(onClick = { onNextButtonIsclicked() }, modifier = Modifier.weight(1f), enabled = selected != "") {
				Text(text = "Next")
			}
		}
	}


}

@Composable
@Preview
fun SelectOptionsScreenPreview() {
	SelectOptionsScreen(options = DataSource.flavors, subtotal = "10", onNextButtonIsclicked = {}, onCancelButtonIsClicked = {}, onSelected = {})
}