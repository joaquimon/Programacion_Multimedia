package com.example.appnavegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                PantallaNavegacion()
            }
        }
    }

data class Contacto(
    val nombre:String,
    val mail:String
)

val contactos = mutableStateListOf<Contacto>(
    Contacto("JoseWi Bullon", "jlbulgon298@gmail.com"),
    Contacto("Paquito Bullon", "pbulgon436@gmail.com"),
    Contacto("Cuajin Bullon", "cbulgon331@gmail.com"),
    Contacto("Furin Bullon", "fbulsar547@gmail.com")
)

@Composable
fun PantallaNavegacion(){
    Spacer(Modifier.size(40.dp))
    val navController = rememberNavController()
    BarraBotones(navController = navController)
    NavHost(navController = navController, startDestination = "pantalla1"){
        composable("pantalla1"){
            Pantalla1(navController)
        }
        composable("pantalla2"){
            Pantalla2(navController)
        }
        composable("pantalla3"){
            Pantalla3(navController)
        }
    }
}

@Composable
fun Pantalla1(navController: NavController){
    Spacer(Modifier.size(40.dp))
    Column {
        BarraBotones(navController = navController)
        Text(text = "")
    }
}

@Composable
fun BarraBotones(navController: NavController){
    Row(horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)){

        }
}