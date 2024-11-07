package com.example.intentyt

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.intentyt.ui.theme.IntentYTTheme

val result: String = """
			Beautiful is better than ugly.
			Explicit is better than implicit.
			Simple is better than complex.
			Complex is better than complicated.
			Flat is better than nested.
			Sparse is better than dense.
			Readability counts.
			Special cases aren't special enough to break the rules.
			Although practicality beats purity.
			Errors should never pass silently.
			Unless explicitly silenced.
			In the face of ambiguity, refuse the temptation to guess.
			There should be one-- and preferably only one --obvious way to do it.
			Although that way may not be obvious at first unless you're Dutch.
			Now is better than never.
			Although never is often better than right now.
			If the implementation is hard to explain, it's a bad idea.
			If the implementation is easy to explain, it may be a good idea.
			Namespaces are one honking great idea -- let's do more of those!
		""".trimIndent()

class SecondActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			IntentYTTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Column(
						modifier = Modifier
							.padding(innerPadding)
							.fillMaxSize()
							.wrapContentSize()
					) {
						Button(onClick = {}) {
							Text("Second Activity")
						}
						//use callingActivity to check if the activity is started for result or not and display the button accordingly
						val isStartedForResult = callingActivity != null
						if (isStartedForResult) {
							Button(onClick = {
								val intent = Intent()
								intent.data = Uri.parse("http://www.youtube.com")
								intent.putExtra("python", result)
								setResult(Activity.RESULT_OK, intent)
								finish()
							}) {
								Text(text = "Send Zen of Python")
							}
						}
					}
				}
			}
		}


	}
}
