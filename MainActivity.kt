package com.example.businesscard

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxHeight()
        .background(Color(0xFFd2e8d4))) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(50.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Avatar(name = "Vijay", jobTitle = "Web dev", modifier = Modifier)
            Contact(modifier = Modifier)
        }
}

//to add
@Composable
fun Avatar(name: String, jobTitle: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)





    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(
            top = 200.dp,

            )
    ) {
        Box(
            modifier = Modifier.background(Color(0xFF073042))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.height(150.dp)
            )
        }
        Text(
            text = name,
            fontSize = 70.sp,
            fontWeight = FontWeight.W300

        )
        Text(
            text = jobTitle,
            fontSize = 28.sp,
            textAlign = TextAlign.End,
            color = Color(0xFF036b38)
        )

    }


}


@Composable
fun Contact(modifier: Modifier = Modifier) {
    Column() {
        Row(
            modifier = Modifier.padding(
                bottom = 10.dp
            )
        ) {
            Icon(
                Icons.Rounded.Call,
                contentDescription = null,
                tint = Color(0xFF036b38),
                modifier = Modifier.padding(
                    end = 10.dp
                )
            )
            Text(
                text = "XXXXXXXXXXXXXXX"
            )
        }
        Row(
            modifier = Modifier.padding(
                bottom = 10.dp
            )
        ) {
            Icon(
                Icons.Rounded.Share,
                contentDescription = null,
                tint = Color(0xFF036b38),
                modifier = Modifier.padding(
                    end = 10.dp
                )
            )

            Text(
                text = "@caege7091(YT)"
            )
        }
        Row(
            modifier = Modifier.padding(
                bottom = 10.dp
            )
        ) {
            Icon(
                Icons.Rounded.Email,
                contentDescription = null,
                tint = Color(0xFF036b38),
                modifier = Modifier.padding(
                    end = 10.dp
                )
            )
            Text(
                text = "mrcapundeadpool@gmail.com"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
//        GreetingText(message = "happy birthday", from = "from kapi")

        Contact()

    }
}