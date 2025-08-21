package ru.sr.poketest.presentation.uiKit.image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PokeBall(
    modifier: Modifier = Modifier
) {
        Box(
            modifier
                .size(100.dp)
                .border(color = Color.DarkGray, width = 2.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            )
            Box(
                Modifier
                    .size(40.dp)
                    .border(color = Color.DarkGray, width = 2.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color.White),
            )
            Box(
                Modifier
                    .size(20.dp)
                    .border(color = Color.DarkGray, width = 1.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color.White),
            )
            Box(
                Modifier
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray),
            )
        }
}

@Preview
@Composable
private fun PokeBallPreview() {
    PokeBall()
}