package com.example.appnavegacionlistadetalle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(10.dp,2.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = Color(0xFFaad59d),
            contentColor = Color.Magenta,
            disabledContentColor = Color.Red,
            disabledContainerColor = Color.Gray
        )
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
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = contacto.mail,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
            Column {
                Image(
                    painter = painterResource(R.drawable.ic_update),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable { contactos.removeAt(indice) }
                )
                Image(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable { contactos.removeAt(indice) }
                )
            }
        }
    }
}

@Composable
fun MostrarLista(navController: NavController){
    Spacer(Modifier.size(40.dp))
    LazyColumn {
        itemsIndexed(contactos){
            indice, contacto -> Tarjeta(indice = indice,contacto = contacto)
        }
    }
}

@Composable
fun InsertarLista(navController: NavController){
    //Variables de estados
    var id by remember{
        mutableIntStateOf(0)
    }
    var nombre by remember{
        mutableStateOf("")
    }
    var mail by remember{
        mutableStateOf("")
    }
    var imagen by remember{
        mutableStateOf("")
    }
    Column {
        Text(
            text = "Insertar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = TextStyle(
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )
        OutlinedTextField(
            value = id.toString(),
            onValueChange = {id = it.toInt()},
            label = {
                Text(text = "ID")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = {
                Text(text = "Nombre")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        OutlinedTextField(
            value = mail,
            onValueChange = {mail = it},
            label = {
                Text(text = "Correo")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        OutlinedTextField(
            value = imagen,
            onValueChange = {imagen = it},
            label = {
                Text(text = "Imagen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        Button(onClick = {
            var nuevoContacto = Contacto(id,nombre,mail,imagen.toInt())
            contactos.add(nuevoContacto)
            id = 0
            nombre = ""
            mail = ""
            imagen = ""

        }) {
            Text(text = "Insertar")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PantallaNavegacion(){
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFaab59d))
    ) {

    Spacer(modifier = Modifier.size(50.dp))
    BarraBotones(navController = navController)
    NavHost(navController = navController, startDestination = "pantalla1", Modifier.offset(0.dp,
        20.dp)){
        composable("pantalla1"){
            MostrarLista(navController)
        }
        composable("pantalla2"){
            InsertarLista(navController)
        }
        composable("pantalla3"){
            Pantalla3(navController)
        }
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
        Text(text = "Home")
        for (x in 1..10){
            Text("10 * $x = ${x * 10}")
        }
    }
}

@Composable
fun BarraBotones(navController: NavController){
    Row(horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()){
        Button(onClick = {
            navController.navigate("pantalla1")
        }) {
            Text("Lista")
        }
        Button(onClick = {
            navController.navigate("pantalla2")
        }) {
            Text("Insertar")
        }
        Button(onClick = {
            navController.navigate("pantalla3")
        }) {
            Text("Home")
        }

    }
}