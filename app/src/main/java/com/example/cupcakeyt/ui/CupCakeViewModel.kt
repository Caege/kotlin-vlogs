package com.example.cupcakeyt.ui

import androidx.lifecycle.ViewModel
import com.example.cupcakeyt.data.CupCakeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val PRICE_PER_CUPCAKE = 2
const val SAME_DAY_PICKUP = 3

class CupCakeViewModel : ViewModel() {
	private val _uiState = MutableStateFlow(CupCakeUiState())
	val uiState = _uiState.asStateFlow()


	fun setQuantity(numberOfCupcakes: Int){
		_uiState.update {
			currentUiState -> currentUiState.copy(quantity = numberOfCupcakes, price = numberOfCupcakes * PRICE_PER_CUPCAKE )
		}
	}

	fun setFlavor(desiredFlavor : String ) {
		_uiState.update { cupCakeUiState ->  cupCakeUiState.copy(flavor = desiredFlavor) }
	}

	fun setPickDate(pickUpdate: String){

		val dateOptions = pickupOptions()
		//same day pickup costs extra

		if(pickUpdate == dateOptions[0]){
			//oops i didn't add values to the date in the video
			_uiState.update { cupCakeUiState ->  cupCakeUiState.copy(date = pickUpdate,price = cupCakeUiState.quantity * PRICE_PER_CUPCAKE + SAME_DAY_PICKUP)}
		} else {_uiState.update { cupCakeUiState -> cupCakeUiState.copy( date = pickUpdate, price = cupCakeUiState.quantity * PRICE_PER_CUPCAKE) }}

	}


	fun pickupOptions () : List<String> {
		val dateOptions = mutableListOf<String>()
		val calendar = Calendar.getInstance()
		val formatter = SimpleDateFormat("EEEE MMMM d", Locale.US)

		repeat(4) {
			dateOptions.add(formatter.format(calendar.time))
			calendar.add(Calendar.DATE, 1)
		}


		return dateOptions
	}


}