package com.oskar.blurry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size
import coil3.svg.SvgDecoder
import com.oskar.blurry.ui.theme.BlurryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.raw.figure)
                        .decoderFactory(SvgDecoder.Factory(useViewBoundsAsIntrinsicSize = false))
                        .size(Size.ORIGINAL)
                        .build(),
                    imageLoader = ImageLoader.Builder(LocalContext.current)
                        .components {
                            add(SvgDecoder.Factory(useViewBoundsAsIntrinsicSize = false))
                        }
                        .build(),
                )

                Text("Image:")
                Image(
                    painter = painter,
                    contentDescription = "Image with painter",
                    contentScale = ContentScale.FillWidth,
                    modifier=Modifier
                        .fillMaxWidth()
                )

                Text("AsyncImage:")
                AsyncImage(
                    model = R.raw.figure,
                    contentDescription = "AsyncImage",
                    contentScale = ContentScale.FillWidth,
                    modifier=Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlurryTheme {
        Greeting("Android")
    }
}