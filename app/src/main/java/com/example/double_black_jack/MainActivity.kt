package com.example.double_black_jack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.double_black_jack.ui.theme.Double_black_jackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Double_black_jackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Display the image with a content description based on the resource name
                    val imageFileName = "my_image" // Replace with the desired image file name (without the extension)
                    CardImageView(imageFileName)
                }
            }
        }
    }
}

@Composable
fun CardImageView(imageFileName: String) {
    val imageView = remember { androidx.appcompat.widget.AppCompatImageView(LocalContext.current) }
    val context = LocalContext.current

    val resourceName = "${context.packageName}:drawable/${imageFileName}"

    val imageResourceId = context.resources.getIdentifier(resourceName, null, null)

    // Calculate the content description based on the file name
    val contentDescription = imageFileName

    DisposableEffect(imageFileName) {
        imageView.contentDescription = contentDescription

        if (imageResourceId != 0) {
            imageView.setImageResource(imageResourceId)
        }

        onDispose { /* Clean up if needed */ }
    }

    // Compose layout to display the ImageView
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AndroidView(
            factory = { imageView },
            modifier = Modifier.fillMaxSize()
        ) { view ->
            // Do any additional configuration of the ImageView here if needed
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardImageViewPreview() {
    Double_black_jackTheme {
        val imageFileName = "my_image" // Replace with the desired image file name (without the extension)
        CardImageView(imageFileName)
    }
}
