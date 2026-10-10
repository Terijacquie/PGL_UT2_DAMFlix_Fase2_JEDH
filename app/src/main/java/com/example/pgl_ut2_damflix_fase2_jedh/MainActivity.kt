package com.example.pgl_ut2_damflix_fase2_jedh

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.pgl_ut2_damflix_fase2_jedh.ui.theme.PGL_UT2_DAMFlix_Fase2_JEDHTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PGL_UT2_DAMFlix_Fase2_JEDHTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormularioPelis(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FormularioPelis(modifier: Modifier = Modifier, valor: String = "") {
    var peli by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var checkeado by remember { mutableStateOf(false) }
    // Para hacer que el botón este en gris si la peli o director está vacío, hay que crear una variable booleana
    val formularioValido = peli.isNotEmpty() && director.isNotEmpty()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            // Esto define el espacio entre los elementos de la columna
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = peli,
                singleLine = true,
                maxLines = 1,
                minLines = 1,
                onValueChange = { peli = it },
                modifier = Modifier
                    // Hace que el campo se extienda a lo ancho de la pantalla
                    .fillMaxWidth(),
                enabled = true,
                readOnly = false, // Permite edición, pero se puede omitir porque por defecto es false
                textStyle = TextStyle(color = Color.Black),
                label = { Text(text = "Título de la película") },
                placeholder = { Text(text = "Título de la película") },
                leadingIcon = {
                    // Para que Icons no me saliera en rojo, implementé una dependencia nueva en Gradle.kts
                    // Una vez instalada la dependencia, te pedirá importar las cosillas.
                    Icon(imageVector = Icons.Default.Movie, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
                },
                isError = true,
                // --- visualTransformation es para password, lo puse porque salia en la documentación
                // en el ejemplo que ponen para saber implmentar el OutlineTextField
                // visualTransformation = passwordVisualTransformation(),
                // Hay varias opciones en KeyboardType (hasta email), pero pondré text que es la que mas se apega al contexto
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                // lo resolvere luego: keyboardActions = KeyboardActions(onSearch = {}),
            )

            OutlinedTextField(
                value = director,
                singleLine = true,
                maxLines = 1,
                minLines = 1,
                onValueChange = { director = it },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = true,
                textStyle = TextStyle(color = Color.Black),
                label = { Text(text = "Director") },
                placeholder = { Text(text = "Director") },
                leadingIcon = {
                    // Para que Icons no me saliera en rojo, implementé una dependencia nueva en Gradle.kts
                    // Una vez instalada la dependencia, te pedirá importar las cosillas.
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
                },
                isError = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            OutlinedTextField(
                value = anio,
                singleLine = true,
                maxLines = 1,
                minLines = 1,
                onValueChange = { anio = it },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = true,
                textStyle = TextStyle(color = Color.Black),
                label = { Text(text = "Año de estreno") },
                placeholder = { Text(text = "Año") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
                },
                isError = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Row(
                // Esta combinación hace que el check aparezca en el medio
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
               Checkbox(
                   checked = checkeado,
                   onCheckedChange = { checkeado = it}
               )
                Text(
                    text = "Visto"
                )
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(),
                // Aqui es donde se pone la condición
                enabled = formularioValido
            ) {
                Text(text= "Guardar Película")
            }



        }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PGL_UT2_DAMFlix_Fase2_JEDHTheme {
        FormularioPelis()
    }
}