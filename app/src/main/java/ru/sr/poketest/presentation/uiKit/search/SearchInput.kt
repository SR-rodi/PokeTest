package ru.sr.poketest.presentation.uiKit.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leftIcon: @Composable () -> Unit? = {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = PokeTheme.colors.darkBlue,
        )
    },
    textStyle: TextStyle = PokeTheme.typography.p.copy(
        color = PokeTheme.colors.darkBlue
    ),
    placeholder: String = ""
) {


    BasicTextField(
        modifier = modifier
            .widthIn(min = 100.dp),
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leftIcon.invoke()

                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier.weight(1f)) {
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = placeholder,
                            style = PokeTheme.typography.p,
                            color = PokeTheme.colors.darkBlue.copy(alpha = 0.5f),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                if (value.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = { onValueChange("") }
                    ) {
                        Icon(
                            modifier = Modifier.size(14.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = PokeTheme.colors.primaryBlueVariant,
                        )
                    }
                }
            }
        }
    )
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun SearchInputPreview() {
    PokeTheme {
        SearchInput(
            value = "test",
            onValueChange = {}
        )
    }
}