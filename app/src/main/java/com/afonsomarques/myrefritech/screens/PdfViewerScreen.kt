package com.afonsomarques.myrefritech.screens

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.afonsomarques.myrefritech.R
import java.io.File

@Composable
fun PdfViewerScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val pdfFile = remember {
        File(context.cacheDir, "manual_carel_easy.pdf").apply {
            context.resources.openRawResource(
                R.raw.manual_carel_easy
            ).use { input ->
                outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

    val fileDescriptor = remember {
        ParcelFileDescriptor.open(
            pdfFile,
            ParcelFileDescriptor.MODE_READ_ONLY
        )
    }

    val renderer = remember {
        PdfRenderer(fileDescriptor)
    }

    DisposableEffect(renderer) {
        onDispose {
            renderer.close()
            fileDescriptor.close()
            pdfFile.delete()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Manual CAREL easy",
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(renderer.pageCount) { pageIndex ->

                val bitmap = remember(renderer, pageIndex) {

                    val page = renderer.openPage(pageIndex)

                    try {
                        val width = 1200

                        val height = (
                                width.toFloat() *
                                        page.height / page.width
                                ).toInt()

                        Bitmap.createBitmap(
                            width,
                            height,
                            Bitmap.Config.ARGB_8888
                        ).also { image ->
                            page.render(
                                image,
                                null,
                                null,
                                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                            )
                        }

                    } finally {
                        page.close()
                    }
                }

                // Estado individual do zoom de cada página
                var scale by remember(renderer, pageIndex) {
                    mutableStateOf(1f)
                }

                var offset by remember(renderer, pageIndex) {
                    mutableStateOf(Offset.Zero)
                }

                // Controla ampliação e movimentação
                val transformState = rememberTransformableState {
                        zoomChange, panChange, _ ->

                    val newScale = (scale * zoomChange)
                        .coerceIn(1f, 4f)

                    scale = newScale

                    offset = if (newScale > 1f) {
                        offset + panChange
                    } else {
                        Offset.Zero
                    }
                }

                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Página ${pageIndex + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            translationX = offset.x
                            translationY = offset.y
                        }
                        .transformable(
                            state = transformState,
                            canPan = { scale > 1f }
                        ),
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Voltar")
        }
    }
}