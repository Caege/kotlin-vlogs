package com.example.coroutinetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutinetest.ui.theme.CoroutineTestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			CoroutineTestTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Counter(modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}
}

@Composable
fun Counter(modifier: Modifier, primeFinder: PrimeUtils = PrimeUtils()) {
	//manages states for values that update in the app
	val number = remember { mutableStateOf(1) }
	val result = remember {
		mutableStateOf("Not ready")
	}

	//see if the user has selected to use the coroutine
	val useCoroutine = remember {
		mutableStateOf(false)
	}

	//hmmm I wonder what this is?
	val coroutineScope = rememberCoroutineScope()

	Box(
		contentAlignment = Alignment.Center, modifier = modifier
			.fillMaxSize()
			.wrapContentSize()
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding
				(horizontal = 32.dp), verticalArrangement = Arrangement.spacedBy(32.dp)
		) {
			Card(shape = RoundedCornerShape(50)) {
				Text(
					number.value.toString(),
					modifier = Modifier.padding(32.dp),
					fontSize = 26.sp,
					fontWeight = FontWeight.Bold
				)
			}


			Button(onClick = { number.value += 1 }) {
				Text("Increment")
			}


			Row(verticalAlignment = Alignment.CenterVertically) {
				Text("Use coroutines")
				Spacer(modifier = Modifier.width(50.dp))
				Switch(
					checked = useCoroutine.value, onCheckedChange = { useCoroutine.value = it },
				)
			}
			//find 20 million prime numbers xD
			Button(onClick = {
				if (useCoroutine.value) {
					coroutineScope.launch(Dispatchers.Default) {

						//a function that finds prime requested number of prime numbers
						primeFinder.findPrimes(20000000, result)
					}
				} else {
					primeFinder.findPrimes(20000000, result)

				}

			}) {
				Text("Find prime numbers")
			}

			Card {
				Text(result.value, modifier = Modifier.padding(16.dp))
			}
		}
	}

}



// a class that contains functions that help us find prime numbers
class PrimeUtils {
	private fun isPrime(num: Int): Boolean {
		if (num <= 1) return false
		for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
			if (num % i == 0) return false
		}
		return true
	}

	fun findPrimes(limit: Int, status: MutableState<String>): List<Int> {
		status.value = "loading"
		val primes = mutableListOf<Int>()
		for (i in 2..limit) {
			if (isPrime(i)) {
				primes.add(i)
			}
		}

		status.value = "requested prime numbers were found"
		return primes
	}
}
