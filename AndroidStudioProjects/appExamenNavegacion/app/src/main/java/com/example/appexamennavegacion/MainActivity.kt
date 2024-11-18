package com.example.appexamennavegacion
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
//@Preview(showBackground = true)
@Composable
fun PantallaNavegacion() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFaab59d)),
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
            .background(color = Color(0xFFaad59d)),
    ) {
        Button(onClick = {
            navController.navigate("pantalla1")
        }) {
            Text(text = "Layouts")
        }
        Button(onClick = {
            navController.navigate("pantalla2")
        }) {
            Text(text = "MiniCalculadora")
        }
        Button(onClick = {
            navController.navigate("pantalla3")
        }) {
            Text(text = "Lista")
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
    val salud:Int,
    val imagen:Int
)
//Creamos una lista mutable de 'Fruta'
var Pokemons = mutableStateListOf<Pokemon>(
    Pokemon(1,"Pikachu",50,R.drawable.pikachu),
    Pokemon(2,"Chikorita", 25, R.drawable.chicorita),
    Pokemon(3,"Charmander",15, R.drawable.charmander),
    Pokemon(4,"Totodile", 60, R.drawable.dragon)
)
@Composable
fun Tarjeta(indice: Int, pokemon: Pokemon, navController: NavController) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp),
        colors = CardColors(
            containerColor = Color(0xFFaad59d),
            contentColor = Color.Magenta,
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
                    navController.navigate("detalle/${pokemon.id}")
                }
        ) {
            Image(
                painter = painterResource(id = (pokemon.imagen).toInt()),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
            Column {
                Text(
                    text = "Pokemon: ${pokemon.nombre}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Salud: ${pokemon.salud.toString()}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
            Column {
                Image(
                    painter = painterResource(R.drawable.ic_update),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        Pokemons.removeAt(indice)
                    }
                )
                Image(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clickable {
                        Pokemons.removeAt(indice)
                    }
                )
            }
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
                indice, pokemon -> Tarjeta(indice = indice, pokemon = pokemon, navController = navController)
        }
    }
}
//Funcion para la tarjeta detalle
@Composable
fun TarjetaDetalle(navController: NavController, id: Int) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp),
        colors = CardColors(
            containerColor = Color(0xFFaad59d),
            contentColor = Color.Magenta,
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
            Text(
                text = "Pokemon: " + Pokemons[id-1].nombre,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 32.sp
            )
            Text(
                text = "Salud: " + Pokemons[id-1].salud,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.size(50.dp))
            Image(
                painter = painterResource(id = Pokemons[id-1].imagen),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.size(50.dp))
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
    var salud by remember {
        mutableStateOf(0)
    }
    var imagen by remember {
        mutableStateOf(0)
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
            value = salud.toString(),
            onValueChange = {salud = it.toInt()},
            label = {
                Text(text = "Salud")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        var selectedOption by remember { mutableStateOf(R.drawable.pikachu) } // Holds selected option
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            content = {
                item {
                    Text("Selecciona una imagen")
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Jigglypuff")
                        RadioButton(
                            selected = selectedOption == R.drawable.bolita,
                            onClick = { selectedOption = R.drawable.bolita }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Bulbasaur")
                        RadioButton(
                            selected = selectedOption == R.drawable.bulbasaur,
                            onClick = { selectedOption = R.drawable.bulbasaur }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Eevee")
                        RadioButton(
                            selected = selectedOption == R.drawable.cabrita,
                            onClick = { selectedOption = R.drawable.cabrita }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Caterpie")
                        RadioButton(
                            selected = selectedOption == R.drawable.caterpie,
                            onClick = { selectedOption = R.drawable.caterpie }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Charmander")
                        RadioButton(
                            selected = selectedOption == R.drawable.charmander,
                            onClick = { selectedOption = R.drawable.charmander }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Chikorita")
                        RadioButton(
                            selected = selectedOption == R.drawable.chicorita,
                            onClick = { selectedOption = R.drawable.chicorita }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Totodile")
                        RadioButton(
                            selected = selectedOption == R.drawable.dragon,
                            onClick = { selectedOption = R.drawable.dragon }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Butterfree")
                        RadioButton(
                            selected = selectedOption == R.drawable.mariposa,
                            onClick = { selectedOption = R.drawable.mariposa }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Meowth")
                        RadioButton(
                            selected = selectedOption == R.drawable.meowth,
                            onClick = { selectedOption = R.drawable.meowth }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Pikachu")
                        RadioButton(
                            selected = selectedOption == R.drawable.pikachu,
                            onClick = { selectedOption = R.drawable.pikachu }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Piplup")
                        RadioButton(
                            selected = selectedOption == R.drawable.piplup,
                            onClick = { selectedOption = R.drawable.piplup }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Vaporeon")
                        RadioButton(
                            selected = selectedOption == R.drawable.sirena,
                            onClick = { selectedOption = R.drawable.sirena }
                        )
                    }
                }
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Snorlax")
                        RadioButton(
                            selected = selectedOption == R.drawable.snorlap,
                            onClick = { selectedOption = R.drawable.snorlap }
                        )
                    }
                }
                //Text("Selected option: $selectedOption")
                imagen = selectedOption

                item {
                    Button(onClick = {
                        var nuevoPokemon = Pokemon(id,nombre,salud,imagen)
                        Pokemons.add(nuevoPokemon)
                        id = 0
                        nombre=""
                        salud=0
                        imagen=0
                        navController.navigate("pantalla3")
                    }) {
                        Text(text = "Insertar")
                    }
                }
                item {
                    Spacer(Modifier.size(40.dp))
                }
            }
        )

    }
}

//Parte Layouts
@Composable
@Preview(showBackground = true)
fun Layouts(){
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Spacer(Modifier.size(20.dp))
            Column {
                Text(text = "ColumnsLayout",
                    modifier = Modifier.size(100.dp).fillMaxWidth())
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(100.dp).background(color = Color.Magenta))
                Text(text = "C",
                    modifier = Modifier.size(150.dp).background(color = Color.Red))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Column(modifier = Modifier.size(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Column(modifier = Modifier.size(200.dp).background(color = Color.LightGray)) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta)
                        .align(Alignment.CenterHorizontally))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red)
                        .align(Alignment.End))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Column(modifier = Modifier.size(200.dp).background(color = Color.Blue)) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow)
                        .weight(1.0f))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta)
                        .weight(2.0f))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red)
                        .weight(3.0f))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Text(text = "RowsLayout",
                modifier = Modifier.size(100.dp).fillMaxWidth())
            Row {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Row(modifier = Modifier.size(200.dp)) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow)
                        .weight(2.0f))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta)
                        .weight(5.0f))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red)
                        .weight(8.0f))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Row(modifier = Modifier.size(200.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Row(modifier = Modifier.size(200.dp).background(color = Color.Black)) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta)
                        .align(Alignment.CenterVertically))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red)
                        .align(Alignment.Bottom))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Text(text = "BoxsLayout",
                modifier = Modifier.size(100.dp).fillMaxWidth())
            Box(modifier = Modifier.size(300.dp)) {
                Text(text = "A",
                    modifier = Modifier.size(150.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(100.dp).background(color = Color.Magenta))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Box(modifier = Modifier.size(300.dp),
                contentAlignment = Alignment.BottomCenter) {
                Text(text = "A",
                    modifier = Modifier.size(150.dp).background(color = Color.Yellow))
                Text(text = "B",
                    modifier = Modifier.size(100.dp).background(color = Color.Magenta))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Box(modifier = Modifier.size(300.dp)) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow)
                        .align(Alignment.TopCenter))
                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta)
                        .align(Alignment.CenterStart))
                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red)
                        .align(Alignment.BottomEnd))
            }
        }
        item {
            Spacer(Modifier.size(20.dp))
            Text(text = "SpacerLayout", modifier = Modifier.size(100.dp).fillMaxWidth())
            Column(modifier = Modifier.size(300.dp)) {
                Text(text = "A",
                    modifier = Modifier.size(50.dp).background(color = Color.Yellow))

                Spacer(Modifier.size(100.dp, 10.dp))

                Text(text = "B",
                    modifier = Modifier.size(50.dp).background(color = Color.Magenta))

                Spacer(Modifier.size(40.dp))

                Text(text = "C",
                    modifier = Modifier.size(50.dp).background(color = Color.Red))
            }
        }
    }
}

@Composable
fun MiniCalculadora() {
    Column (modifier = Modifier.fillMaxSize()
        .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center
    ) {
        var num1 by remember{
            mutableStateOf("")
        }

        var num2 by remember{
            mutableStateOf("")
        }

        var resultado by remember{
            mutableStateOf("")
        }

        Text(text = "LA MEGACALCULADORA MARAVITUPENDA", modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
            color = Color.Blue
        )

        OutlinedTextField(
            value = num1,
            onValueChange = {num1 = it},
            label = {
                Text(text = "Número 1")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            singleLine = true
        )

        OutlinedTextField(
            value = num2,
            onValueChange = {num2 = it},
            label = {
                Text(text = "Número 2")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            singleLine = true
        )

        Row () {
            Button(
                onClick = {
                    var suma = 0
                    suma = num1.toInt() + num2.toInt()
                    resultado = suma.toString()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Sumar")
            }

            Button(
                onClick = {
                    var resta = 0
                    resta = num1.toInt() - num2.toInt()
                    resultado = resta.toString()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Resta")
            }

            Button(
                onClick = {
                    var multiplicacion = 0
                    multiplicacion = num1.toInt() * num2.toInt()
                    resultado = multiplicacion.toString()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Multiplicar")
            }
        }

        TextField(
            value = resultado,
            onValueChange = {resultado = it},
            label = {
                Text(text = "Resultado")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )    }
}