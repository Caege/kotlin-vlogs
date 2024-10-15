package com.example.viewmodeltest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodeltest.R

@Composable
fun CounterScreen(modifier: Modifier = Modifier, counterViewModel: CounterViewModel = viewModel()) {

	val counterUiState = counterViewModel.uiState.collectAsState()
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Column(modifier = Modifier.width(130.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
			IconButton(onClick = { counterViewModel.reset() }, modifier = Modifier.align(Alignment.End)) {
				Icon(painter = painterResource(R.drawable.history_24dp_e8eaed_fill0_wght400_grad0_opsz24), contentDescription = null)
			}
			Spacer(modifier = Modifier.height(50.dp))
			Card(
				modifier = Modifier
					.height(100.dp)
					.width(100.dp)
			) {
				Text(
					text = "${counterUiState.value.value}", modifier = Modifier
						.fillMaxSize()
						.wrapContentSize(Alignment.Center), fontSize = 30.sp
				)
			}
			Spacer(modifier = Modifier.height(50.dp))
			Button(onClick = { counterViewModel.increment() }) {
				Text(text = "Increment")
			}
		}
	}

}