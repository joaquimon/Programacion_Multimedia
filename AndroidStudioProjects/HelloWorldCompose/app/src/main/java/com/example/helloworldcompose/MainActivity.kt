package com.example.helloworldcompose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           Saludar();
            MyIconButton();
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Saludar() {
    LazyColumn( modifier =  Modifier.fillMaxSize().paint(painter = painterResource(id =R.drawable.fondosonic),contentScale = ContentScale.FillBounds).padding(vertical = 120.dp), ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.sonic),
                contentDescription = "Sonic el erizo",
            )
        }

        item {
            Text(text = "Hola mundo", modifier = Modifier.padding(10.dp).fillMaxWidth(), color = Color.Black, fontSize = 32.sp, textAlign = TextAlign.Center)
        }

        item {
            Text(
                text = "Mensaje nuevo",
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                color = Color.Black,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }

        item {
            Text(text = "Otro mensaje más", modifier = Modifier.padding(10.dp).fillMaxWidth(), color = Color.Black, fontSize = 32.sp, textAlign = TextAlign.Center)
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.sonic),
                contentDescription = "Sonic el erizo",
            )
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.sonic),
                contentDescription = "Sonic el erizo",
            )
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.sonic),
                contentDescription = "Sonic el erizo",
            )
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.sonic),
                contentDescription = "Sonic el erizo",
            )
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.sonic),
                contentDescription = "Sonic el erizo",
            )
        }
    }
}

@Composable
fun MyIconButton() {
    val activity = (LocalContext.current as? Activity)
    IconButton(
        onClick = { activity?.finish() },
        modifier = Modifier.size(60.dp).padding(vertical = 20.dp),
    ){
        Icon(imageVector = Icons.Filled.Close, contentDescription = "Settings")
    }
}

