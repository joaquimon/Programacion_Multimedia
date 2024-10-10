package com.example.ejemploestados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemploestados.ui.theme.EjemploEstadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            app()
        }
    }
}

@Composable
fun app(){
    var counter by rememberSaveable { mutableStateOf(0) }
     Column (modifier = Modifier.fillMaxSize().background(color = Color.Cyan).padding(20.dp)) {
         Row (modifier = Modifier
             .fillMaxWidth()
             .background(Color.Yellow)
             .padding(top = 8.dp)
         ) {
            Image(painter = painterResource(id = R.drawable.ic_favorite), contentDescription = "Corazón", modifier = Modifier.clickable { counter++ }.size(200.dp))
             Text(text = counter.toString(), modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterVertically), textAlign = TextAlign.Center, fontSize = 75.sp)
         }
         Row (modifier = Modifier
             .fillMaxWidth()
             .background(Color.Yellow)
             .padding(top = 16.dp)) {
             Image(painter = painterResource(id = R.drawable.ic_broken), contentDescription = "Corazon Roto", modifier = Modifier.clickable { counter-- }.size(200.dp))
             Text(text = "Este botón reduce tus puntos", modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterVertically), textAlign = TextAlign.Center, fontSize = 30.sp)
         }
     }
 }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploEstadosTheme {
        app()
    }
}