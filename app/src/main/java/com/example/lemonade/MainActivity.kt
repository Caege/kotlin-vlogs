package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                var currentStep by remember { mutableStateOf(1) }

                var squeezeCount by remember { mutableStateOf(0) }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Lemonade",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )


                    }


                ) { innerPadding ->

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(MaterialTheme.colorScheme.primary),
                        color = MaterialTheme.colorScheme.background
                    )
                    {
                        when (currentStep) {
                            1 -> {
                                LemonTextAndImage(
                                    drawableResource = R.drawable.lemon_tree,
                                    onImageClick = {
                                        currentStep = 2
                                        squeezeCount = (2..4).random()
                                    }

                                )
                            }
                            2 -> {
                                LemonTextAndImage(
                                    drawableResource = R.drawable.lemon_squeeze,
                                    onImageClick = {
                                        squeezeCount--
                                        if (squeezeCount == 0) {
                                            currentStep = 3
                                        }
                                    }

                                )
                            }

                            3 -> {
                                LemonTextAndImage(
                                    drawableResource = R.drawable.lemon_drink,
                                    onImageClick = {
                                        currentStep = 4

                                    }

                                )
                            }

                            4 -> {
                                LemonTextAndImage(
                                    drawableResource = R.drawable.lemon_restart,
                                    onImageClick = {
                                      currentStep = 1
                                    }

                                )
                            }

                        }
                    }

                }
            }
        }
    }
}

//@Composable
//fun LemonadeApp( modifier: Modifier = Modifier) {
//    var step by remember { mutableStateOf(1) }
//    var result =  R.drawable.lemon_tree
//    val squeeze = (2..4).random()
//    var squeezeClick by remember { mutableStateOf(0) }
//   result = when (step) {
//        1 -> R.drawable.lemon_tree
//        2 -> R.drawable.lemon_squeeze
//        3 -> R.drawable.lemon_drink
//        4 -> R.drawable.lemon_restart
//        else -> R.drawable.lemon_tree
//    }
//    Column {
//        Box (modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.Green)
//            .height(100.dp), ) {
//            Row(horizontalArrangement = Arrangement.Center, modifier = modifier , verticalAlignment = Alignment.Bottom) {
//                Spacer( modifier = Modifier.height(20.dp))
//                Text(
//                    text = "Lemonade",
//
//
//                    )
//            }
//        }
//
//        Column( modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//            Button(onClick = {
//
//                if(step == 2) {
//                    squeezeClick++
//                    if(squeeze == squeezeClick) {
//                        step++
//                    }
//                } else if (step == 4) {
//                    step = 1
//                    squeezeClick = 0
//                }
//                else {
//                    step++
//            }
//
//            }, modifier = Modifier.background(Color.Green)) {
//                Image(
//                    painter = painterResource(result),
//                    contentDescription = step.toString()
//                )
//            }
//            Spacer( modifier = Modifier.height(20.dp))
//            Text(
//                text = "Tap the lemon tree to select a lemon"
//            )
//        }
//
//    }
//
//}


//@Composable
//fun LemonApp(modifier: Modifier = Modifier) {
//
//    var currentStep by remember { mutableStateOf(1) }
//    var squeezeCount by remember { mutableStateOf(0) }
//
//
//
//    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//
//        Text(text = "Hello there")
//    }
//}

@Composable
fun LemonTextAndImage(
    textLabel: String = "This text appears at the bottom of the image",
    drawableResource: Int,
    contentDescription: String = "shit image",
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor  = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResource),
                    contentDescription = contentDescription,
                    modifier = Modifier.width(100.dp)
                )

            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = textLabel,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
