package com.example.quiz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuizScreen(modifier: Modifier = Modifier, quizViewModel: QuizViewModel = viewModel()) {
	val quizUiState = quizViewModel.uiState.collectAsState()

	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp), contentAlignment = Alignment.Center
	) {
		Column {
			Card(
				modifier = Modifier
					.align(alignment = Alignment.End)
					.height(40.dp)
					.width(40.dp)
			) {
				Text(
					text = quizUiState.value.score.toString(), modifier = Modifier
						.fillMaxSize()
						.wrapContentSize(Alignment.Center)
				)
			}
			Spacer(modifier = Modifier.height(16.dp))
			Card(
				modifier = Modifier
					.height(100.dp)
					.fillMaxWidth(),
				shape = MaterialTheme.shapes.medium
			) {
				Text(text = quizUiState.value.currentQuestion.question, modifier = Modifier.fillMaxSize().padding(8.dp).wrapContentSize())
			}


			Spacer(modifier = Modifier.height(50.dp))

			LazyVerticalGrid(
				columns = GridCells.Fixed(2),
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			) {
				items(4) {
					Button(onClick = { quizViewModel.checkAnswer(quizUiState.value.currentQuestion.options.get(it)) }) {
						Text(text = quizUiState.value.currentQuestion.options.get(it))
					}
				}
			}
//		(0..3).forEach {
//			Button(onClick = { /*TODO*/ }) {
//				Text(text = quizUiState.value.currentQuestion.options.get(it))
//			}
			Spacer(modifier = Modifier.height(50.dp))
			Row(modifier = Modifier.padding(horizontal = 100.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
				OutlinedButton(onClick = {}) {
					Text(text = "Skip")
				}
				Spacer(modifier = Modifier.weight(1f))
				Button(onClick = { quizViewModel.nextQuestion() }) {
					Text(text = "Next")
				}
			}
		}
	}


}
