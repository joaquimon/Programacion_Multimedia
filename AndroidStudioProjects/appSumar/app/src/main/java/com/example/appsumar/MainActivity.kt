package com.example.appsumar

import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appsumar.ui.theme.AppSumarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sumar2()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Sumar2() {
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
                Text(text = "Suma")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )    }
}