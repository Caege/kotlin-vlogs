package com.example.cupcakeyt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcakeyt.data.DataSource
import com.example.cupcakeyt.ui.CupCakeViewModel
import com.example.cupcakeyt.ui.SelectOptionsScreen
import com.example.cupcakeyt.ui.StartOrderScreen
import com.example.cupcakeyt.ui.SummeryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupCakeScreen() {
CupCakeApp()
}

enum class CupCakeRoutes( val title : String ){
	Start("Select amount"), Flavor("Select Flavor"),PickUp("Select pickup date"),Summery("Order summery")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupCakeApp(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), cupCakeViewModel: CupCakeViewModel = viewModel()) {

	val backStackEntry  by navController.currentBackStackEntryAsState()
	val currentScreen = backStackEntry?.destination?.route

	val canNavigateBack = navController.previousBackStackEntry

	if (currentScreen != null) {
		CupCakeRoutes.valueOf(currentScreen)
	}

	Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
		TopAppBar(title = {

			Row ( verticalAlignment = Alignment.CenterVertically){

				if(canNavigateBack != null) {
					IconButton(
						onClick = {navController.navigateUp()}
					) {
						Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
					}
				}



				if (currentScreen != null) {
					val current =  CupCakeRoutes.valueOf(currentScreen)

					Text(text = current.title)
				}
			}


		},  colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			titleContentColor = MaterialTheme.colorScheme.primary,
		))
	}) { innerPadding ->

		val subtotal = cupCakeViewModel.uiState.collectAsState().value.price

		NavHost(navController = navController, startDestination = CupCakeRoutes.Start.name, modifier = Modifier.padding(innerPadding)) {
			composable(CupCakeRoutes.Start.name){
				StartOrderScreen(quantityOption = DataSource.quantityOptions, onSelectQuantity = {numberOfcupcakes : Int ->
					navController.navigate(CupCakeRoutes.Flavor.name)
					cupCakeViewModel.setQuantity(numberOfcupcakes)})
			}

			composable(CupCakeRoutes.Flavor.name){
				SelectOptionsScreen(options = DataSource.flavors, subtotal = subtotal.toString(), onSelected = {flavor : String -> cupCakeViewModel.setFlavor(flavor)}, onNextButtonIsclicked = { navController.navigate(CupCakeRoutes.PickUp.name)}, onCancelButtonIsClicked = {navController.navigate(CupCakeRoutes.Start.name)})
			}

			composable(CupCakeRoutes.PickUp.name){
				SelectOptionsScreen(options = cupCakeViewModel.pickupOptions(), subtotal = subtotal.toString(),onNextButtonIsclicked = { navController.navigate(CupCakeRoutes.Summery.name)},onCancelButtonIsClicked = {navController.navigate(CupCakeRoutes.Start.name)}, onSelected = {userpick -> cupCakeViewModel.setPickDate(userpick)})
			}

			composable(CupCakeRoutes.Summery.name){
				SummeryScreen(cupCakeUiState = cupCakeViewModel.uiState.collectAsState().value,  onCancelIsClicked = {navController.navigate(CupCakeRoutes.Start.name)})
			}
		}



	}









}