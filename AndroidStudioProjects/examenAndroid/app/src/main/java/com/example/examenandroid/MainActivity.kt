package com.example.examenandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Preview(showBackground = true)
@Composable
fun PantallaNavegacion() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFaab5dd)),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        BarraBotones(navController = navController)
        NavHost(navController = navController, startDestination = "pantalla3", Modifier.offset(0.dp,
            20.dp)) {
            composable("pantalla1"){
                Layouts()
            }
            composable("pantalla2"){
                MiniCalculadora()
            }
            composable("pantalla3"){
                MostrarLista(navController = navController)
            }
            composable("insertar"){
                InsertarLista(navController = navController)
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
            .background(color = Color.Magenta)
    ) {
        Button(onClick = {
            navController.navigate("pantalla1")
        }) {
            Text(text = "Ejer01")
        }
        Button(onClick = {
            navController.navigate("pantalla2")
        }) {
            Text(text = "Ejer02")
        }
        Button(onClick = {
            navController.navigate("pantalla3")
        }) {
            Text(text = "Ejer03")
        }
    }
}

@Composable
fun BotonInsertar(navController: NavController){
    Row(
        horizontalArrangement = Arrangement.Start
    ) {
        Button(onClick = {
            navController.navigate("insertar")
        }) {
            Text(text = "Nuevo Pokémon")
        }
    }
}
// Nuestra aplicación
//Data class
data class Pokemon(
    val id:Int,
    val nombre:String,
    val bando:String,
    val imagen:Int
)
//Creamos una lista mutable de 'Fruta'
var Pokemons = mutableStateListOf<Pokemon>(
    Pokemon(1,"Pikachu","De los buenos",R.drawable.pikachu),
    Pokemon(2,"Chikorita", "De los buenos", R.drawable.chicorita),
    Pokemon(3,"Charmander","De los buenos", R.drawable.charmander),
    Pokemon(4,"Totodile", "De los malos", R.drawable.dragon)
)
@Composable
fun Tarjeta(indice: Int, pokemon: Pokemon) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp),
        colors = CardColors(
            containerColor = Color.Magenta,
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
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        Pokemons.removeAt(indice)
                    }
                )
            }

            Column {
                Text(
                    text = "Pokemon: ${pokemon.nombre}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Bando: ${pokemon.bando.toString()}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
            Image(
                painter = painterResource(id = (pokemon.imagen).toInt()),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
@Composable
fun MostrarLista(navController: NavController) {
    LazyColumn {
        item {
            BotonInsertar(navController = navController)
            Spacer(Modifier.size(20.dp))
        }
        itemsIndexed(Pokemons){
                indice, pokemon -> Tarjeta(indice = indice, pokemon = pokemon)
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
    var bando by remember {
        mutableStateOf("")
    }
    var imagen by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.padding(10.dp).background(Color.Magenta)) {
        Text(text = "Insertar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp)
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
            value = bando,
            onValueChange = {bando = it},
            label = {
                Text(text = "Descripción")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        var selectedOption by remember { mutableStateOf(R.drawable.pikachu) } // Holds selected option
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start)
        {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Bolita")
                        RadioButton(
                            selected = selectedOption == R.drawable.bolita,
                            onClick = { selectedOption = R.drawable.bolita }
                        )
                        Text("Bulbasaur")
                        RadioButton(
                            selected = selectedOption == R.drawable.bulbasaur,
                            onClick = { selectedOption = R.drawable.bulbasaur }
                        )
                        Text("Cabrita")
                        RadioButton(
                            selected = selectedOption == R.drawable.cabrita,
                            onClick = { selectedOption = R.drawable.cabrita }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Caterpie")
                        RadioButton(
                            selected = selectedOption == R.drawable.caterpie,
                            onClick = { selectedOption = R.drawable.caterpie }
                        )
                        Text("Charmander")
                        RadioButton(
                            selected = selectedOption == R.drawable.charmander,
                            onClick = { selectedOption = R.drawable.charmander }
                        )
                        Text("Chicorita")
                        RadioButton(
                            selected = selectedOption == R.drawable.chicorita,
                            onClick = { selectedOption = R.drawable.chicorita }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Dragon")
                        RadioButton(
                            selected = selectedOption == R.drawable.dragon,
                            onClick = { selectedOption = R.drawable.dragon }
                        )
                        Text("Mariposa")
                        RadioButton(
                            selected = selectedOption == R.drawable.mariposa,
                            onClick = { selectedOption = R.drawable.mariposa }
                        )
                        Text("Meowth")
                        RadioButton(
                            selected = selectedOption == R.drawable.meowth,
                            onClick = { selectedOption = R.drawable.meowth }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Pikachu")
                        RadioButton(
                            selected = selectedOption == R.drawable.pikachu,
                            onClick = { selectedOption = R.drawable.pikachu }
                        )
                        Text("Piplup")
                        RadioButton(
                            selected = selectedOption == R.drawable.piplup,
                            onClick = { selectedOption = R.drawable.piplup }
                        )
                        Text("Sirena")
                        RadioButton(
                            selected = selectedOption == R.drawable.sirena,
                            onClick = { selectedOption = R.drawable.sirena }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Snorlap")
                        RadioButton(
                            selected = selectedOption == R.drawable.snorlap,
                            onClick = { selectedOption = R.drawable.snorlap }
                        )
                    }
                //Text("Selected option: $selectedOption")
                imagen = selectedOption
                    Button(onClick = {
                        var nuevoPokemon = Pokemon(id,nombre, bando,imagen)
                        Pokemons.add(nuevoPokemon)
                        id = 0
                        nombre=""
                        bando=""
                        imagen=0
                        navController.navigate("pantalla3")
                    }) {
                        Text(text = "Insertar")
                    }
                    Spacer(Modifier.size(40.dp))

            }

    }
}

//Parte Layouts
@Composable
fun Layouts(){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.size(20.dp))
        Row(modifier = Modifier.size(220.dp, 100.dp).background(Color.Magenta),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "1",
                modifier = Modifier.size(50.dp).background(Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
            Spacer(Modifier.size(10.dp))
            Text(text = "2",
                modifier = Modifier.size(50.dp).background(Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
            Spacer(Modifier.size(10.dp))
            Text(text = "3",
                modifier = Modifier.size(50.dp).background(Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
        }
        Spacer(Modifier.size(20.dp))
        Box(modifier = Modifier.size(150.dp).background(Color.Magenta),
            contentAlignment = Alignment.Center) {
            Text(text = "4",
                modifier = Modifier.size(120.dp).background(color = Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
            Text(text = "5",
                modifier = Modifier.size(70.dp).background(color = Color.Magenta),
                textAlign = TextAlign.Center,
                color = Color(0xFFaab5dd))
            Text(text = "6",
                modifier = Modifier.size(25.dp).background(color = Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
        }
        Spacer(Modifier.size(20.dp))
        Column(modifier = Modifier.size(30.dp, 90.dp).background(Color.Magenta),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "7",
                modifier = Modifier.size(20.dp).background(color = Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
            Spacer(Modifier.size(10.dp))
            Text(text = "8",
                modifier = Modifier.size(20.dp).background(color = Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
            Spacer(Modifier.size(10.dp))
            Text(text = "9",
                modifier = Modifier.size(20.dp).background(color = Color(0xFFaab5dd)),
                textAlign = TextAlign.Center,
                color = Color.Magenta)
        }
    }
}

@Composable
fun MiniCalculadora() {
    Column (modifier = Modifier.size(500.dp)
        .background(color = Color.Magenta),
        verticalArrangement = Arrangement.Center
    ) {
        var horaInicio by remember{
            mutableStateOf("")
        }

        var horaFin by remember{
            mutableStateOf("")
        }

        var horasDescanso by remember{
            mutableStateOf("")
        }

        var pagaHora = 10

        var sueldo by remember{
            mutableStateOf("")
        }

        Row(modifier = Modifier.size(450.dp, 70.dp).background(Color(0xFFaab5dd)),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Calcular salario", modifier = Modifier.padding(10.dp).align(
                Alignment.CenterVertically),
                fontSize = 40.sp,
                color = Color.Magenta
            )
        }
        

        OutlinedTextField(
            value = horaInicio,
            onValueChange = {horaInicio = it},
            label = {
                Text(text = "Hora inicio")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            singleLine = true
        )

        OutlinedTextField(
            value = horaFin,
            onValueChange = {horaFin = it},
            label = {
                Text(text = "Hora fin")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            singleLine = true
        )

        OutlinedTextField(
            value = horasDescanso,
            onValueChange = {horasDescanso = it},
            label = {
                Text(text = "Tiempo descanso")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            singleLine = true
        )

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    var suma = 0
                    suma = (horaFin.toInt() - horaInicio.toInt() - horasDescanso.toInt()) * pagaHora
                    sueldo = suma.toString()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Salario")
            }
        }

        TextField(
            value = sueldo,
            onValueChange = {sueldo = it},
            label = {
                Text(text = "Salario")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )    }
}