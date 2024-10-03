package com.example.suma

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.suma.ui.theme.SumaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SumaTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Estados") }
                        )
                    }) { innerPadding ->
                    Suma(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Suma(name: String, modifier: Modifier = Modifier) {

    var a by rememberSaveable() { mutableStateOf("") }
    var b by rememberSaveable() { mutableStateOf("") }
    var resultado by rememberSaveable() { mutableStateOf("Resultado") }
    Column(
        modifier.fillMaxSize(),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
       // Box(Modifier.height(20.dp))
        Text(text = resultado, fontSize = 20.sp)
        LeeDato(numero = a, onTextChange = { a = it })
        LeeDato(numero = b, onTextChange = { b = it })
        Sumar(a, b) { resultado = it.toString() }
        Restar(a, b) { resultado = it.toString() }
    }
}

@Composable
fun Sumar(a: String, b: String, onClick: (Int) -> Unit) {

    Button(onClick = {
        try {
            onClick(a.toInt() + b.toInt())
        } catch (e: Exception) {
            Log.d("error", "Error resta")
            onClick(0)
        }


    }) { Text("sumar") }
}


@Composable
fun Restar(a: String, b: String, onClick: (Int) -> Unit) {

    Button(onClick = {
        try {
            onClick(a.toInt() - b.toInt())
        } catch (e: Exception) {
            Log.d("error", "Error suma")
            onClick(0)
        }


    }) { Text("restar") }
}

@Composable
fun LeeDato(numero: String, onTextChange: (String) -> Unit) {
    TextField(
        value = numero, onValueChange = {
            if (it.isDigitsOnly())
                onTextChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SumaTheme {
        Suma("Android")
    }
}