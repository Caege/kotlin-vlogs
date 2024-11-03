package com.example.intentyt

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.intentyt.ui.theme.IntentYTTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			IntentYTTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Column (modifier = Modifier.fillMaxSize().wrapContentSize()){
						val context = LocalContext.current
						Button(onClick = {}, modifier = Modifier.padding(innerPadding)) {
							Text("Main Activity")
						}

						Button(onClick = { openSecondActivity(context) }) {
							Text("Launch second activity")
						}
					}
				}
			}
		}
	}


}

//explicit intent
//fun openSecondActivity(context: Context){
//	val intent  = Intent(context,SecondActivity::class.java)
//	context.startActivity(intent)
//}


//implicit intent
fun openSecondActivity(context: Context) {
	try {
		val intent = Intent(Intent.ACTION_SEND)
		intent.type = "*/*"

		intent.addCategory(Intent.CATEGORY_DEFAULT)
//		intent.putExtra("person", "Caege")
		intent.putExtra(Intent.EXTRA_SUBJECT, "this is a subject")
		intent.putExtra(Intent.EXTRA_TEXT, "this is the body")
		context.startActivity(intent)
	} catch (e: ActivityNotFoundException) {
		Toast.makeText(context, "no apps found", Toast.LENGTH_LONG).show()
	}

}

