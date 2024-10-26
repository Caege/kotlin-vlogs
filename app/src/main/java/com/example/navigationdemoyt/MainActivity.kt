package com.example.navigationdemoyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemoyt.ui.theme.NavigationDemoYTTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			NavigationDemoYTTheme {
				NavigationDemoApp()
			}
		}
	}
}

enum class Screen(val title : String) {
	SCREEN1(title = "Screen 1"),SCREEN2(title = "Screen 2"),SCREEN3("Screen 3")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDemoApp(modifier: Modifier = Modifier , navController : NavHostController = rememberNavController()) {
	val backStackEntry by navController.currentBackStackEntryAsState()

	val currentRoute = backStackEntry?.destination?.route
	val canNavigateBack = navController.previousBackStackEntry != null

Scaffold(topBar = { TopAppBar(
	colors = TopAppBarDefaults.topAppBarColors(
		containerColor = MaterialTheme.colorScheme.primaryContainer,
		titleContentColor = MaterialTheme.colorScheme.primary
	),

	title = {
Row(verticalAlignment = Alignment.CenterVertically) {

	if(canNavigateBack) {
		IconButton(
			onClick = {navController.navigateUp()}
		) {
			Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
		}

	}

	if (currentRoute != null) {
		Text(text = currentRoute)
	}
}

})}) {
	innerPadding ->

	NavHost(navController = navController, startDestination = Screen.SCREEN1.title, modifier = Modifier.padding(innerPadding)) {
		composable(route = Screen.SCREEN1.title) {
			Screen1(onNextButtonIsClicked = { navController.navigate(Screen.SCREEN2.title)})
		}

		composable(route = Screen.SCREEN2.title) {
			Screen2(onNextButtonIsClicked = {navController.navigate(Screen.SCREEN3.title)}, onPreviousButtonIsClicked = {navController.navigate(Screen.SCREEN1.name)})
		}
		composable(route = Screen.SCREEN3.title) {
			Screen3(onDoneButtonIsClicked = {navController.navigate(Screen.SCREEN1.title)})
		}
	}
}
}





@Composable
fun Screen1(onNextButtonIsClicked : () -> Unit) {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
			Card(
				modifier = Modifier
					.height(150.dp)
					.fillMaxWidth()
					.padding(horizontal = 50.dp)
			) {
				Text(
					text = "Screen 1", modifier = Modifier
						.fillMaxSize()
						.wrapContentSize()
				)
			}

			Spacer(modifier = Modifier.height(50.dp))
			Row {
//				OutlinedButton(onClick = { onPreviousButtonClicked() }) {
//					Text(text = "previous")
//				}
				Spacer(modifier = Modifier.width(50.dp))
				Button(onClick = {onNextButtonIsClicked()}) {
					Text(text = "next")
				}
			}
		}
	}
}


@Composable
fun Screen2(onNextButtonIsClicked: () -> Unit,  onPreviousButtonIsClicked : () -> Unit) {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
			Card(
				modifier = Modifier
					.height(150.dp)
					.fillMaxWidth()
					.padding(horizontal = 50.dp)
			) {
				Text(
					text = "Screen 2", modifier = Modifier
						.fillMaxSize()
						.wrapContentSize()
				)
			}

			Spacer(modifier = Modifier.height(50.dp))
			Row {
				OutlinedButton(onClick = { onPreviousButtonIsClicked() }) {
					Text(text = "previous")
				}
				Spacer(modifier = Modifier.width(50.dp))
				Button(onClick = {onNextButtonIsClicked()}) {
					Text(text = "next")
				}
			}
		}
	}
}



@Composable
fun Screen3(onDoneButtonIsClicked : () -> Unit) {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
			Card(
				modifier = Modifier
					.height(150.dp)
					.fillMaxWidth()
					.padding(horizontal = 50.dp)
			) {
				Text(
					text = "Screen 3", modifier = Modifier
						.fillMaxSize()
						.wrapContentSize()
				)
			}

			Spacer(modifier = Modifier.height(50.dp))
			Row {

				Spacer(modifier = Modifier.width(50.dp))
				Button(onClick = {onDoneButtonIsClicked()}) {
					Text(text = "done")
				}
			}
		}
	}
}


