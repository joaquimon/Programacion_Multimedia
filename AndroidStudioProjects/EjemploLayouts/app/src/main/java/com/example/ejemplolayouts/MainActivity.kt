package com.example.ejemplolayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplolayouts.ui.theme.EjemploLayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColumnsExample4()
        }
    }
}

@Composable
@Preview
fun ColumnsExample1(){
    Column (modifier = Modifier.size(300.dp)) {
        Text(text = "A",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFfff59d)))
        Text(text = "B",
            modifier = Modifier.size(100.dp).background(color = Color(0xFFffe082)))
        Text(text = "C",
            modifier = Modifier.size(150.dp).background(color = Color(0xFFffcc80)))
    }
}

@Composable
@Preview
fun ColumnsExample2(){
    Column (modifier = Modifier.size(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "A",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFfff59d)))
        Text(text = "B",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFffe082)))
        Text(text = "C",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFffcc80)))
    }
}

@Composable
@Preview
fun ColumnsExample3(){
    Column (modifier = Modifier.size(200.dp).background(color = Color.Magenta)) {
        Text(text = "A",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFfff59d)))
        Text(text = "B",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFffe082))
                .align(Alignment.CenterHorizontally))
        Text(text = "C",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFffcc80))
                .align(Alignment.End))
    }
}

@Composable
@Preview
fun ColumnsExample4(){
    Column (modifier = Modifier.size(200.dp).background(color = Color.Magenta)) {
        Text(text = "A",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFfff59d)).weight(1.0f))
        Text(text = "B",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFffe082)).weight(2.0f))
        Text(text = "C",
            modifier = Modifier.size(50.dp).background(color = Color(0xFFffcc80)).weight(3.0f))
    }
}