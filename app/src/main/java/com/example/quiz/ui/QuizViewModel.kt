package com.example.quiz.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quiz.data.QuizUiState
import com.example.quiz.data.clientQuestions
import com.example.quiz.data.questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel : ViewModel() {
	 var currentIndex by mutableStateOf(1)
		private set



	private val _uiState = MutableStateFlow(QuizUiState(currentQuestion = clientQuestions.get(currentIndex)))

	val uiState = _uiState.asStateFlow()
//	private val currentAnswer = _uiState.value.currentQuestion.correctAnswer
private val currentAnswer : String
	get() = questions.get(currentIndex).correctAnswer




	fun checkAnswer(userAnswer : String) {
		Log.d("test", "checking answer")
		Log.d("test", "$userAnswer and $currentAnswer")
		if(userAnswer === currentAnswer) {

			_uiState.value = _uiState.value.copy(score = _uiState.value.score + 1)
		}
	}


	fun nextQuestion() {
		currentIndex += 1
		_uiState.value = _uiState.value.copy(currentQuestion = clientQuestions.get(currentIndex ))
	}

}