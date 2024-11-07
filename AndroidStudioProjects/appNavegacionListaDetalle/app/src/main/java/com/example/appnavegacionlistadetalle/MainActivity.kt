package com.example.appnavegacionlistadetalle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnavegacionlistadetalle.ui.theme.AppNavegacionListaDetalleTheme

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
    val id:Int,
    val nombre:String,
    val mail:String,
    val imagen:Int
)

//Creamos una lista mutable de 'Contacto'
val contactos = mutableStateListOf<Contacto>(
    Contacto(1,"JoseWi Bullon", "jlbulgon298@gmail.com", R.drawable.miku),
    Contacto(2,"Paquito Bullon", "pbulgon436@gmail.com",R.drawable.miku2),
    Contacto(3,"Cuajin Bullon", "cbulgon331@gmail.com",R.drawable.spiderman),
    Contacto(4,"Furin Bullon", "fbulsar547@gmail.com",R.drawable.ironman)
)

//Tarjeta para los items
@Composable
fun Tarjeta(indice: Int, contacto: Contacto){
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)) {
            Image(
                painter = painterResource(id = contacto.imagen),
                contentDescription = "Imagen chupichuli",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(75.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = contacto.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = contacto.mail,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun MostrarLista(navController: NavController){
    LazyColumn {
        itemsIndexed(contactos){
            indice, contacto -> Tarjeta(indice = indice,contacto = contacto)
        }
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
            MostrarLista(navController)
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
        Text(text = "Lista")
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
        Text(text = "Insertar")
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
        Text(text = "Home")
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