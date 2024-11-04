package com.example.intentyt

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.intentyt.ui.theme.IntentYTTheme

const val ZEN = 10
const val FILEPICKER = 20

class MainActivity : ComponentActivity() {
	var rules = mutableStateOf<String?>(null)
	override fun onCreate(savedInstanceState: Bundle?) {
		fun openZenOfPython() {
			val intent = Intent(this, SecondActivity::class.java)

			startActivityForResult(intent, ZEN)
		}

		fun openFilePicker() {
			val intent = Intent(Intent.ACTION_GET_CONTENT)
			intent.type = "*/*"
			startActivityForResult(intent, FILEPICKER)
		}


		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			IntentYTTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val context = LocalContext.current
					//use lazycolums to allow overflowing content
					LazyColumn(
						modifier = Modifier
							.fillMaxSize()
							.padding(horizontal = 16.dp)
							.padding(innerPadding)
							.wrapContentSize(),
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						//use item functions to manually add Lazy items
						item {
							Button(onClick = {}, modifier = Modifier.padding(innerPadding)) {
								Text("Main Activity")
							}
						}

						item {
							Button(onClick = { openSecondActivity(context) }) {
								Text("Launch second activity")
							}
						}

						item {
							Button(onClick = { openGmail(context) }) {
								Text("Open Gmail")
							}
						}
						item {
							Button(onClick = { openZenOfPython() }) {
								Text(text = "Get Zen of Python")
							}
						}


						item {
							Button(onClick = { openFilePicker() }) {
								Text("Open File picker")
							}
						}

						item {
							Spacer(modifier = Modifier.height(50.dp))
						}
						if (rules.value != null) {
							item {
								Card() {
									Text(text = rules.value!!, modifier = Modifier.padding(8.dp))
								}
							}

						}
					}
				}
			}
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)


		// handle all sorts of incoming results
		if (requestCode == ZEN && resultCode == Activity.RESULT_OK) {
			rules.value = data?.getStringExtra("python")


		}

		if (requestCode == FILEPICKER && resultCode == Activity.RESULT_OK) {
			rules.value = data?.data.toString()
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
		val intent = Intent(context, SecondActivity::class.java)

		context.startActivity(intent)
	} catch (e: ActivityNotFoundException) {
		Toast.makeText(context, "no apps found", Toast.LENGTH_LONG).show()
	}

}

fun openGmail(context: Context) {
	try {
		val intent = Intent(Intent.ACTION_SEND)
		intent.type = "*/*"

		intent.addCategory(Intent.CATEGORY_DEFAULT)
//		intent.putExtra("person", "Caege")
		intent.putExtra(Intent.EXTRA_SUBJECT, "They are killing us , man")
		intent.putExtra(Intent.EXTRA_TEXT, "Press the bombo, over")
		context.startActivity(intent)
	} catch (e: ActivityNotFoundException) {
		Toast.makeText(context, "no apps found", Toast.LENGTH_LONG).show()
	}

}

