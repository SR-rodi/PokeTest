package ru.sr.poketest.presentation.uiKit.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun CircleIconButton(
    imageVector: ImageVector,
    iconSize: Dp = 30.dp,
    modifier: Modifier = Modifier,
    buttonSize: Dp = 45.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape)
            .background(PokeTheme.colors.primaryBlue)
            .shadow(elevation = 2.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = imageVector,
            contentDescription = "Search",
            tint = PokeTheme.colors.with,
        )
    }
}

@Preview
@Composable
private fun CircleIconButtonPreview() {
    PokeTheme {
        CircleIconButton(
            imageVector = Icons.Default.Search,
            onClick = {}
        )
    }
}