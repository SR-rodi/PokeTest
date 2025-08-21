package ru.sr.poketest.presentation.uiKit.commonComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun ActionChip(
    text: String,
    color: Color,
    rightIcon: ImageVector = Icons.Default.ArrowDropDown,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = color)
                .clickable(onClick = onClick)
                .padding(top = 6.dp, bottom = 6.dp, start = 8.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = PokeTheme.typography.p,
            )
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = rightIcon,
                contentDescription = "ActionChipIcon",
                tint = PokeTheme.colors.darkBlue,
            )
        }

    }
}

@Preview
@Composable
private fun ActionChipPreview() {
    ActionChip(
        text = "test",
        color = Color.Red,
        onClick = {}
    )
}