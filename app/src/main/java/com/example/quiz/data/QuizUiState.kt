package com.example.quiz.data

data class QuizUiState(val currentQuestion : QuestionInterface = clientQuestions.get(1), val score : Int = 0) { }


interface  QuestionInterface {
	val question: String
	val options : List<String>
}

//val question: String,
//val options: List<String>,  // A list of 4 options
//val correctAnswer: String   // The correct answer



