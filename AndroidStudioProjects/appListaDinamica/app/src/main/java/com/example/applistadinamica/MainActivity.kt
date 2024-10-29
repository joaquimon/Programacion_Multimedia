package com.example.applistadinamica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdministrarContactos();
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
@Preview
fun AdministrarContactos(){
    var nombre by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    Column() {
        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = {Text("Nombre de contacto")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        OutlinedTextField(
            value = mail,
            onValueChange = {mail = it},
            label = {Text("Email")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Button(onClick = {
            val nuevoContacto = Contacto(nombre,mail)
            contactos.add(nuevoContacto)
            nombre=""
            mail=""
        }, modifier = Modifier.padding(5.dp)) {
            Text(text = "Agregar",modifier = Modifier.fillMaxWidth())
        }
        LazyColumn() {
            items(contactos){contacto->
                MostrarContacto(contacto)
            }
        }
    }


}

@Composable
fun MostrarContacto(miContacto: Contacto) {
    Text(text = miContacto.nombre)
    Text(text = miContacto.mail)
    HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .width(4.dp), color = Color.Black)
}