
package com.example.approbots
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaNavegacion()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PantallaNavegacion() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFA5CCDF)),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        BarraBotones(navController = navController)
        NavHost(navController = navController, startDestination = "pantalla1", Modifier.offset(0.dp,
            20.dp)) {
            composable("pantalla1"){
                MostraLista(navController = navController)
            }
            composable("pantalla2"){
                InsertarLista(navController = navController)
            }
            composable("insertar"){
                InsertarLista(navController = navController)
            }
            composable("detalle/{id}"){ backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
                TarjetaDetalle(navController = navController, id)
            }
        }
    }
}
@Composable
fun BarraBotones(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Cyan),
    ) {
        Button(onClick = {
            navController.navigate("pantalla1")
        }) {
            Text(text = "Lista")
        }
        Button(onClick = {
            navController.navigate("pantalla2")
        }) {
            Text(text = "Insertar")
        }
    }
}
// Nuestra aplicación
//Data class
data class Contacto(
    val id:Int,
    val nombre:String,
    val email:String
)
//Creamos una lista mutable de 'Fruta'
var Contactos = mutableStateListOf<Contacto>(
    Contacto(1,"Martinez Luis","mluis@gmail.com"),
    Contacto(2,"Rodriguez Pablo", "rpablo@gmail.com"),
    Contacto(3,"Conesa Nestor","cnestor@hotmail.com"),
    Contacto(4,"Lopez Ana", "lana@yahoo.com")
)
@Composable
fun Tarjeta(indice: Int, contacto: Contacto, navController: NavController) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp),
        colors = CardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Blue,
            disabledContentColor = Color.Red,
            disabledContainerColor = Color.Gray
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable {
                    navController.navigate("detalle/${contacto.id}")
                }
        ) {
            AsyncImage(
                model = "https://www.robohash.org/" + contacto.nombre,
                contentDescription = "Robot " + contacto.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
            Column {
                Text(
                    text = "Contacto: ${contacto.nombre}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Email: ${contacto.email}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Column {
                Image(
                    painter = painterResource(R.drawable.ic_update),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        Contactos.removeAt(indice)
                    }
                )
                Image(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        Contactos.removeAt(indice)
                    }
                )
            }
        }
    }
}
@Composable
fun MostraLista(navController: NavController) {
    LazyColumn {
        itemsIndexed(Contactos){
                indice, contacto -> Tarjeta(indice = indice, contacto = contacto, navController)
        }
    }
}
//Funcion para la tarjeta detalle
@Composable
fun TarjetaDetalle(navController: NavController, indice:Int) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp),
        colors = CardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Blue,
            disabledContentColor = Color.Red,
            disabledContainerColor = Color.Gray
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.size(50.dp))
            AsyncImage(
                model = "https://www.robohash.org/" + Contactos[indice - 1].nombre,
                contentDescription = "Robot " + Contactos[indice - 1].nombre,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(50.dp))
            Text(
                text = "Contacto: " + Contactos[indice - 1].nombre,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 32.sp
            )
            Text(
                text = "Email: " + Contactos[indice - 1].email,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navController.navigate("pantalla1")
            }) {
                Text(text = "VOLVER")
            }
        }
    }
}
@Composable
fun InsertarLista(navController: NavController) {
    //Variables de estados
    var id by remember {
        mutableStateOf(0)
    }
    var nombre by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Insertar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            style = TextStyle(
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp)
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
            value = email,
            onValueChange = {email = it},
            label = {
                Text(text = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        Button(onClick = {
            var nuevoContacto = Contacto(id,nombre,email)
            Contactos.add(nuevoContacto)
            id = 0
            nombre=""
            email=""
            navController.navigate("pantalla1")
        }) {
            Text(text = "Insertar")
        }
    }
}
 