package ru.sr.poketest.presentation.uiKit.progress

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import ru.sr.poketest.presentation.uiKit.image.PokeBall

@Composable
fun DefaultProgressIndicator(modifier: Modifier = Modifier) {

    var isRotating by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotating) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )
    )

    LaunchedEffect(Unit) {
        isRotating = !isRotating
    }

    PokeBall(
        modifier = modifier
            .graphicsLayer {
                rotationZ = rotationAngle
            }
    )
}

@Preview
@Composable
private fun DefaultProgressIndicatorPreview() {
    DefaultProgressIndicator()
}
