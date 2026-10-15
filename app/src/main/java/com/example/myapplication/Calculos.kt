package com.example.appoperaciones
fun calcular(a: Double, b: Double, operacion: String): String {
    val resultado = when (operacion) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        else -> {
            if (b == 0.0) return "Error"
            a / b
        }
    }
    return if (resultado % 1 == 0.0) resultado.toLong().toString() else resultado.toString()
}