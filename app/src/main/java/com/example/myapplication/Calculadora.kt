package com.example.appoperaciones

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var numeroActual by rememberSaveable { mutableStateOf("") }
    var numeroAnterior by rememberSaveable { mutableStateOf("") }
    var operacion by rememberSaveable { mutableStateOf("") }
    var acabaDeCalcular by rememberSaveable { mutableStateOf(false) }
    val expresion = numeroAnterior+" "+operacion+" "+numeroActual
    val resultadoTiempoReal =
        if (numeroAnterior.toDoubleOrNull() != null &&
            numeroActual.toDoubleOrNull() != null &&
            operacion.isNotEmpty()
        ) {
            calcular(numeroAnterior.toDouble(), numeroActual.toDouble(), operacion)
        } else {
            ""
        }

    fun pulsarNumero(n: String) {
        if (acabaDeCalcular) {
            numeroActual = n
            acabaDeCalcular = false
        } else {
            numeroActual += n
        }
    }

    fun pulsarOperacion(op: String) {
        acabaDeCalcular = false
        if (numeroActual.isEmpty()) {
            if (numeroAnterior.isNotEmpty()) operacion = op
        } else if (numeroAnterior.isNotEmpty() && operacion.isNotEmpty()) {
            val r = calcular(numeroAnterior.toDouble(), numeroActual.toDouble(), operacion)
            if (r != "Error") {
                numeroAnterior = r
                operacion = op
                numeroActual = ""
            }
        } else {
            numeroAnterior = numeroActual
            operacion = op
            numeroActual = ""
        }
    }

    fun pulsarIgual() {
        if (numeroAnterior.isNotEmpty() && operacion.isNotEmpty() && numeroActual.isNotEmpty()) {
            val r = calcular(numeroAnterior.toDouble(), numeroActual.toDouble(), operacion)
            if (r != "Error") {
                numeroActual = r
                numeroAnterior = ""
                operacion = ""
                acabaDeCalcular = true
            }
        }
    }

    fun pulsarBorrar() {
        acabaDeCalcular = false
        if (numeroActual.isNotEmpty()) {
            numeroActual = numeroActual.dropLast(1)
        } else if (operacion.isNotEmpty()) {
            numeroActual = numeroAnterior
            numeroAnterior = ""
            operacion = ""
        }
    }

    fun pulsarLimpiar() {
        numeroActual = ""
        numeroAnterior = ""
        operacion = ""
        acabaDeCalcular = false
    }

    PantallaCalculadora(
        expresion = expresion,
        resultadoTiempoReal = resultadoTiempoReal,
        onNumero = { pulsarNumero(it) },
        onOperacion = { pulsarOperacion(it) },
        onIgual = { pulsarIgual() },
        onBorrar = { pulsarBorrar() },
        onLimpiar = { pulsarLimpiar() },
        modifier = modifier
    )
}