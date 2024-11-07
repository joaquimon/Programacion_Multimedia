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
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
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

//Creamos una lista mutable de 'Contacto'
val contactos = mutableStateListOf<Contacto>(
    Contacto("JoseWi Bullon", "jlbulgon298@gmail.com"),
    Contacto("Paquito Bullon", "pbulgon436@gmail.com"),
    Contacto("Cuajin Bullon", "cbulgon331@gmail.com"),
    Contacto("Furin Bullon", "fbulsar547@gmail.com")
)

//Tarjeta para los items
@Composable
fun Tarjeta(indice: Int, contacto: Contacto, navController: NavController){
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier) {  }
    }
}

@Composable
@Preview(showBackground = true)
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
        Text(text = "Tabla del 2")
        for (x in 1..10){
            Text("2 * $x = ${x * 2}")
        }
    }
}

@Composable
fun Pantalla2(navController: NavController){
    Spacer(Modifier.size(40.dp))
    Column {
        BarraBotones(navController = navController)
        Text(text = "Tabla del 5")
        for (x in 1..10){
            Text("5 * $x = ${x * 5}")
        }
    }
}

@Composable
fun Pantalla3(navController: NavController){
    Spacer(Modifier.size(40.dp))
    Column {
        BarraBotones(navController = navController)
        Text(text = "Tabla del 10")
        for (x in 1..10){
            Text("10 * $x = ${x * 10}")
        }
    }
}

@Composable
fun BarraBotones(navController: NavController){
    Row(horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
            .padding(50.dp)){
        Button(onClick = {
            navController.navigate("pantalla1")
        }) {
            Text("Tabla 2")
        }
        Button(onClick = {
            navController.navigate("pantalla2")
        }) {
            Text("Tabla 5")
        }
        Button(onClick = {
            navController.navigate("pantalla3")
        }) {
            Text("Tabla 10")
        }

        }
}