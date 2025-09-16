package com.gabsee.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabsee.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(
    modifier: Modifier = Modifier
) {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Select") }
    var outputUnit by remember { mutableStateOf("Select") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val inputConversionFactor = remember { mutableStateOf(1.00) }
    val outputConversionFactor = remember { mutableStateOf(1.00) }


    fun convertUnit() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inputConversionFactor.value * 100.0 / outputConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = {
                Text("Enter Value")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box {
                Button(
                    onClick = {
                        inputExpanded = true
                    }
                ) {
                    Text(
                        text = when (inputUnit) {
                            "Centimeters" -> "Centimeters"
                            "Meters" -> "Meters"
                            else -> "Select"
                        }
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = inputExpanded,
                    onDismissRequest = { inputExpanded = !inputExpanded }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text("Centimeters")
                        },
                        onClick = {
                            inputUnit = "Centimeters"
                            inputExpanded = !inputExpanded
                            inputConversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Meters")
                        },
                        onClick = {
                            inputUnit = "Meters"
                            inputExpanded = !inputExpanded
                            inputConversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(
                    onClick = {
                        outputExpanded = true
                    }
                ) {
                    Text( when (outputUnit) {
                        "Centimeters" -> "Centimeters"
                        "Meters" -> "Meters"
                        else -> "Select"
                    } )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = outputExpanded,
                    onDismissRequest = { outputExpanded = !outputExpanded }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text("Centimeters")
                        },
                        onClick = {
                            outputUnit = "Centimeters"
                            outputExpanded = !outputExpanded
                            outputConversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Meters")
                        },
                        onClick = {
                            outputUnit = "Meters"
                            outputExpanded = !outputExpanded
                            outputConversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result: $outputValue ${if (outputUnit == "Select") "" else outputUnit}",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
