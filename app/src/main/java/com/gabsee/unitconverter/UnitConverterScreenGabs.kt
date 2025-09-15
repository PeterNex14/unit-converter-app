package com.gabsee.unitconverter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabsee.unitconverter.ui.theme.UnitConverterTheme

@Composable
fun UnitConverterScreenGabs(
    modifier: Modifier = Modifier
) {
    var expandedLeft by remember { mutableStateOf(false) }
    var expandedRight by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Unit Converter"
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = {
                Text("Enter Value")
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(45.dp),
        ) {
            Button(
                onClick = { expandedLeft = !expandedLeft },
            ) {
                Text(
                    text = "Centimeters",
                    modifier = Modifier.padding(4.dp)
                )
            }
            DropdownMenu(
                expanded = expandedLeft,
                onDismissRequest = { expandedLeft = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Centimeters")
                    },
                    onClick = {

                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Meters")
                    },
                    onClick = {

                    }
                )
            }
            Button(
                onClick = { expandedRight = !expandedRight }
            ) {
                Text(
                    text = "Meters",
                    modifier = Modifier.padding(4.dp)
                )
            }
            DropdownMenu(
                expanded = expandedRight,
                onDismissRequest = { expandedRight = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Centimeters")
                    },
                    onClick = {

                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Meters")
                    },
                    onClick = {

                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterScreenGabsPreview(

) {
    UnitConverterTheme {
        UnitConverterScreenGabs()
    }
}

