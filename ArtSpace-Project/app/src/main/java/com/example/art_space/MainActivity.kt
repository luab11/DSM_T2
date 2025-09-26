package com.example.art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_space.ui.theme.Art_SpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Art_SpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

// Data class para representar una obra de arte
data class Artwork(
    val id: Int,
    val title: String,
    val artist: String,
    val year: String,
    val imageRes: Int
)

// Lista de obras de arte (ejemplo)
val artworkList = listOf(
    Artwork(
        id = 1,
        title = "Noche estrellada",
        artist = "Vincent van Gogh",
        year = "1889",
        imageRes = R.drawable.artwork1 // Reemplaza con tus recursos
    ),
    Artwork(
        id = 2,
        title = "La persistencia de la memoria",
        artist = "Salvador Dalí",
        year = "1931",
        imageRes = R.drawable.artwork2 // Reemplaza con tus recursos
    ),
    Artwork(
        id = 3,
        title = "El grito",
        artist = "Edvard Munch",
        year = "1893",
        imageRes = R.drawable.artwork3 // Reemplaza con tus recursos
    )
)

@Composable
fun ArtSpaceApp() {
    var currentArtworkIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworkList[currentArtworkIndex]

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Sección 1: Imagen de la obra de arte
            ArtworkImageSection(
                artwork = currentArtwork,
                modifier = Modifier.weight(0.7f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Sección 2: Información de la obra
            ArtworkInfoSection(
                artwork = currentArtwork,
                modifier = Modifier.weight(0.15f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Sección 3: Controles de navegación
            NavigationControlsSection(
                currentIndex = currentArtworkIndex,
                totalArtworks = artworkList.size,
                onPreviousClick = {
                    currentArtworkIndex = if (currentArtworkIndex > 0) {
                        currentArtworkIndex - 1
                    } else {
                        artworkList.size - 1 // Volver al final
                    }
                },
                onNextClick = {
                    currentArtworkIndex = if (currentArtworkIndex < artworkList.size - 1) {
                        currentArtworkIndex + 1
                    } else {
                        0 // Volver al inicio
                    }
                },
                modifier = Modifier.weight(0.15f)
            )
        }
    }
}

@Composable
fun ArtworkImageSection(artwork: Artwork, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        tonalElevation = 8.dp,
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = "Obra de arte: ${artwork.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}

@Composable
fun ArtworkInfoSection(artwork: Artwork, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = artwork.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${artwork.artist} (${artwork.year})",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun NavigationControlsSection(
    currentIndex: Int,
    totalArtworks: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text("Anterior")
        }

        Text(
            text = "${currentIndex + 1}/$totalArtworks",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        Button(
            onClick = onNextClick,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text("Siguiente")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpaceAppPreview() {
    Art_SpaceTheme {
        ArtSpaceApp()
    }
}

@Preview(showBackground = true, showSystemUi = true, widthDp = 800, heightDp = 400)
@Composable
fun ArtSpaceAppTabletPreview() {
    Art_SpaceTheme {
        ArtSpaceApp()
    }
}