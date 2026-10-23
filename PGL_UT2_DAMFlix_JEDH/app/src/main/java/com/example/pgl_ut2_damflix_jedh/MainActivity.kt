package com.example.pgl_ut2_damflix_jedh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pgl_ut2_damflix_jedh.ui.theme.PGL_UT2_DAMFlix_JEDHTheme
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.shape.CircleShape
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                PGL_UT2_DAMFlix_JEDHTheme {
                    CriticosAutores(
                        stringResource(id = R.string.rol1),
                        stringResource(id = R.string.películasVistas1),
                        stringResource(id = R.string.perfilNombre1),
                        stringResource(id = R.string.resenias1),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun CriticosAutores(
        rol: String,
        pelis: String,
        nombre: String,
        resenias: String,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.perfil1),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Column {
                Text(
                    text = stringResource(id = R.string.perfilNombre1)
                )
                Text(
                    text = stringResource(id = R.string.rol1)
                )
                Text(
                    text = stringResource(id = R.string.películasVistas1)
                )
                Text(
                    text = stringResource(id = R.string.resenias1)
                )
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PGL_UT2_DAMFlix_JEDHTheme {
            CriticosAutores("Critico", "21", "Rabi", "13")
        }
    }
}