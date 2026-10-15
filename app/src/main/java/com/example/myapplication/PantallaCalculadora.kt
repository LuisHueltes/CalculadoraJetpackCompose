package com.example.appoperaciones

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun PantallaCalculadora(
    expresion: String,
    resultadoTiempoReal: String,
    onNumero: (String) -> Unit,
    onOperacion: (String) -> Unit,
    onIgual: () -> Unit,
    onBorrar: () -> Unit,
    onLimpiar: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (titulo, pantalla, resultadoRef) = createRefs()
        val (bC, bBorrar, bDiv, bMul, b7, b8, b9, bMenos) = createRefs()
        val (b4, b5, b6, bMas, b1, b2, b3, bIgual, b0) = createRefs()

        val g1 = createGuidelineFromStart(0.25f)
        val g2 = createGuidelineFromStart(0.5f)
        val g3 = createGuidelineFromStart(0.75f)

        Text(
            text = "Calculadora",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.constrainAs(titulo) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = if (expresion.isBlank()) "0" else expresion,
            fontSize = 36.sp,
            modifier = Modifier.constrainAs(pantalla) {
                end.linkTo(parent.end)
                bottom.linkTo(resultadoRef.top)
            }
        )
        Text(
            text = when {
                resultadoTiempoReal == "Error" -> "No se puede dividir entre 0"
                resultadoTiempoReal.isEmpty() -> " "
                else -> "= $resultadoTiempoReal"
            },
            fontSize = 22.sp,
            modifier = Modifier.constrainAs(resultadoRef) {
                end.linkTo(parent.end)
                bottom.linkTo(bC.top, margin = 16.dp)
            }
        )

        BotonCalculadora(
            "0",
            Modifier.constrainAs(b0) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("0") }

        BotonCalculadora(
            "1",
            Modifier.constrainAs(b1) {
                bottom.linkTo(b0.top)
                start.linkTo(parent.start)
                end.linkTo(g1)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("1") }

        BotonCalculadora(
            "2",
            Modifier.constrainAs(b2) {
                bottom.linkTo(b0.top)
                start.linkTo(g1)
                end.linkTo(g2)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("2") }

        BotonCalculadora(
            "3",
            Modifier.constrainAs(b3) {
                bottom.linkTo(b0.top)
                start.linkTo(g2)
                end.linkTo(g3)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("3") }

        BotonCalculadora(
            "=",
            Modifier.constrainAs(bIgual) {
                bottom.linkTo(b0.top)
                start.linkTo(g3)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) { onIgual() }

        BotonCalculadora(
            "4",
            Modifier.constrainAs(b4) {
                bottom.linkTo(b1.top)
                start.linkTo(parent.start)
                end.linkTo(g1)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("4") }

        BotonCalculadora(
            "5",
            Modifier.constrainAs(b5) {
                bottom.linkTo(b1.top)
                start.linkTo(g1)
                end.linkTo(g2)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("5") }

        BotonCalculadora(
            "6",
            Modifier.constrainAs(b6) {
                bottom.linkTo(b1.top)
                start.linkTo(g2)
                end.linkTo(g3)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("6") }

        BotonCalculadora(
            "+",
            Modifier.constrainAs(bMas) {
                bottom.linkTo(b1.top)
                start.linkTo(g3)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) { onOperacion("+") }

        BotonCalculadora(
            "7",
            Modifier.constrainAs(b7) {
                bottom.linkTo(b4.top)
                start.linkTo(parent.start)
                end.linkTo(g1)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("7") }

        BotonCalculadora(
            "8",
            Modifier.constrainAs(b8) {
                bottom.linkTo(b4.top)
                start.linkTo(g1)
                end.linkTo(g2)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("8") }

        BotonCalculadora(
            "9",
            Modifier.constrainAs(b9) {
                bottom.linkTo(b4.top)
                start.linkTo(g2)
                end.linkTo(g3)
                width = Dimension.fillToConstraints
            }
        ) { onNumero("9") }

        BotonCalculadora(
            "-",
            Modifier.constrainAs(bMenos) {
                bottom.linkTo(b4.top)
                start.linkTo(g3)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) { onOperacion("-") }

        BotonCalculadora(
            "C",
            Modifier.constrainAs(bC) {
                bottom.linkTo(b7.top)
                start.linkTo(parent.start)
                end.linkTo(g1)
                width = Dimension.fillToConstraints
            }
        ) { onLimpiar() }

        BotonCalculadora(
            "borrar",
            Modifier.constrainAs(bBorrar) {
                bottom.linkTo(b7.top)
                start.linkTo(g1)
                end.linkTo(g2)
                width = Dimension.fillToConstraints
            }
        ) { onBorrar() }

        BotonCalculadora(
            "/",
            Modifier.constrainAs(bDiv) {
                bottom.linkTo(b7.top)
                start.linkTo(g2)
                end.linkTo(g3)
                width = Dimension.fillToConstraints
            }
        ) { onOperacion("/") }

        BotonCalculadora(
            "*",
            Modifier.constrainAs(bMul) {
                bottom.linkTo(b7.top)
                start.linkTo(g3)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) { onOperacion("*") }
    }
}