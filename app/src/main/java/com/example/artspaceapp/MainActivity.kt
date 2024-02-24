package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val paintingResourceIds = arrayOf(
        R.drawable.monalisa,
        R.drawable.starrynight,
        R.drawable.lasmeninas,
    )

    val paintings = arrayOf(
        "Mona Lisa",
        "The Starry Night",
        "Las Meninas"
    )

    val artists = arrayOf(
        "Leonardo da Vinci",
        "Vincent van Gogh",
        "Diego Velázquez"
    )

    val dates = arrayOf(
        "(1503 to 1519)",
        "(1889)",
        "(1656)"
    )

    var state by remember{ mutableStateOf( 0) }
    val resourceId = paintingResourceIds[state]
    val painting = paintings[state]
    val artist = artists[state]
    val date = dates[state]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Display(resourceId)

        Description(painting, artist, date)

        Controller(
            next = { state = if(state == 2) 0 else state + 1 },
            previous = { state = if(state == 0) 2 else state - 1 }
        )
    }
}

@Composable
fun Display(resourceId: Int) {
    Surface(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = painterResource(resourceId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight(0.5f)
                .padding(32.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Description(painting: String, artist: String, date: String) {
    Surface (
        color = androidx.compose.ui.graphics.Color.LightGray,
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.painting, painting),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.artist, artist),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic
            )

            Text(
                text = stringResource(id = R.string.date, date),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun Controller(next: () -> Unit, previous: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = previous,
            modifier = Modifier.defaultMinSize(minWidth = 150.dp),
        ) {
            Text(text = "<< Previous")
        }

        Button(
            onClick = next,
            modifier = Modifier.defaultMinSize(minWidth = 150.dp)
        ) {
            Text(text = "Next >>")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}