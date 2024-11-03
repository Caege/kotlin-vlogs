package com.example.intentyt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.intentyt.ui.theme.IntentYTTheme

class SecondActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			IntentYTTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Button(onClick = {}, modifier = Modifier.padding(innerPadding).fillMaxSize().wrapContentSize()) {
						Text("Second Activity")
					}
				}
			}
		}
		val result: String? = intent.getStringExtra("person")

		if (result != null) {
			Toast.makeText(this, result, Toast.LENGTH_LONG).show()
		}


	}
}
