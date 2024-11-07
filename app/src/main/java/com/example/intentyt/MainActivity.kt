package com.example.intentyt

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.intentyt.ui.theme.IntentYTTheme

class MainActivity : ComponentActivity() {
	var imageUri by mutableStateOf<Uri?>(null)

	//default contract
	val openContentLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { result: Uri? ->
		imageUri = result
	}

	//default contract that lets use custom intents as input and activity result as output
	val customIntentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
		if (result.resultCode == Activity.RESULT_OK) {
			val intent = result.data
			val content = intent?.data
			Toast.makeText(this, content.toString(), Toast.LENGTH_LONG).show()

		}
	}

	//use custom contract
	val zenLauncher = registerForActivityResult(OpenZen()) {
		if (it != null) {
			Toast.makeText(this, it, Toast.LENGTH_LONG).show()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			IntentYTTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val context = LocalContext.current

					Column(
						modifier = Modifier
							.fillMaxSize()
							.padding(horizontal = 16.dp)
							.padding(innerPadding)
							.wrapContentSize(),
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Button(onClick = {}, modifier = Modifier.padding(innerPadding)) {
							Text("Main Activity")
						}

						Button(onClick = {
							openContentLauncher.launch("*/*")
						}) {
							Text("get content")
						}

						Button(onClick = {
							customIntentLauncher.launch(Intent(Intent.ACTION_GET_CONTENT).apply { type = "*/*" })
						}) {
							Text("custom get content")
						}

						Button(
							onClick = {
								zenLauncher.launch()
							}
						) {
							Text("get zen")
						}

						//use asyncImage from coil library to display images with content scheme. I talk about this in the bonus video
						Box {
							AsyncImage(model = ImageRequest.Builder(context).data(imageUri).build(), contentDescription = null, modifier = Modifier.size(200.dp).clickable {
								val intent = Intent(Intent.ACTION_VIEW).apply { setDataAndType(imageUri, "image/*")

									//give the launched activity read permission
								flags = Intent.FLAG_GRANT_READ_URI_PERMISSION}
								context.startActivity(intent)

							 }, contentScale = ContentScale.Fit, )
						}
					}
				}
			}
		}
	}


}

// implementing our own custom contract
class OpenZen : ActivityResultContract<Unit, String?>() {
	override fun createIntent(context: Context, input: Unit): Intent {
		return Intent(context, SecondActivity::class.java)
	}

	override fun parseResult(resultCode: Int, intent: Intent?): String? {
		if (resultCode != Activity.RESULT_OK) {
			null
		}
		return intent?.getStringExtra("python")
	}

}



