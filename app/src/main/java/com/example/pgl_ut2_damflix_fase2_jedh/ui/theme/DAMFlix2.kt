package com.example.pgl_ut2_damflix_fase2_jedh.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pgl_ut2_damflix_fase2_jedh.R

class DAMFlix2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                PGL_UT2_DAMFlix_Fase2_JEDHTheme {
                    CriticosAutores(
                        rol = stringResource(id = R.string.rol1),
                        pelis = stringResource(id = R.string.películasVistas1),
                        nombre = stringResource(id = R.string.perfilNombre1),
                        resenias = stringResource(id = R.string.resenias1),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CriticosAutores(
    rol: String = stringResource(id = R.string.rol1),
    pelis: String = stringResource(id = R.string.películasVistas1),
    nombre: String = stringResource(id = R.string.perfilNombre1),
    resenias: String = stringResource(id = R.string.resenias1),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.perfil_1),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = nombre)
            Text(text = rol)
            Text(text = pelis)
            Text(text = resenias)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CriticosAutoresPreview() {
    PGL_UT2_DAMFlix_Fase2_JEDHTheme {
        CriticosAutores("Crítico", "21", "Rabi", "13")
    }
}
