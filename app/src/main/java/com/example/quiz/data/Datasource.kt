package com.example.quiz.data



data class Question(
	val question: String,
	val options: List<String>,  // A list of 4 options
	val correctAnswer: String   // The correct answer
)


val questions = listOf(
	Question(
		question = "What is the capital of Japan?",
		options = listOf("Seoul", "Tokyo", "Beijing", "Bangkok"),
		correctAnswer = "Tokyo"
	),
	Question(
		question = "Which gas do plants absorb from the atmosphere?",
		options = listOf("Oxygen", "Nitrogen", "Carbon Dioxide", "Helium"),
		correctAnswer = "Carbon Dioxide"
	),
	Question(
		question = "Who painted the Mona Lisa?",
		options = listOf("Pablo Picasso", "Vincent van Gogh", "Leonardo da Vinci", "Claude Monet"),
		correctAnswer = "Leonardo da Vinci"
	),
	Question(
		question = "What is the hardest natural substance on Earth?",
		options = listOf("Gold", "Iron", "Diamond", "Granite"),
		correctAnswer = "Diamond"
	),
	Question(
		question = "Which planet is closest to the Sun?",
		options = listOf("Earth", "Mercury", "Mars", "Venus"),
		correctAnswer = "Mercury"
	),
	Question(
		question = "What is the square root of 64?",
		options = listOf("6", "7", "8", "9"),
		correctAnswer = "8"
	),
	Question(
		question = "Which country won the FIFA World Cup in 2018?",
		options = listOf("Germany", "Brazil", "France", "Argentina"),
		correctAnswer = "France"
	),
	Question(
		question = "What is the chemical symbol for water?",
		options = listOf("HO", "O2", "H2O", "CO2"),
		correctAnswer = "H2O"
	),
	Question(
		question = "What is the largest continent by land area?",
		options = listOf("Africa", "North America", "Asia", "Europe"),
		correctAnswer = "Asia"
	),
	Question(
		question = "Which element has the atomic number 1?",
		options = listOf("Oxygen", "Hydrogen", "Helium", "Carbon"),
		correctAnswer = "Hydrogen"
	)
)

class ClientQuestion  (  override val question : String ,  override val options : List<String> ) : QuestionInterface {

}


val clientQuestions = questions.map { it -> ClientQuestion(it.question, it.options) }



class TestQuestion  (  override val question : String ,  override val options : List<String> ) : QuestionInterface {

}

val testQuestion = questions.map { it -> TestQuestion(it.question, it.options) }






