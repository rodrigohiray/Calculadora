package com.TPSI_VFX.l_calculadorarodrigo

sealed class CalculatorOperation (val symbol: String) {
    object Add: CalculatorOperation(symbol = "+")
    object Subtract: CalculatorOperation(symbol = "-")
    object Multiply: CalculatorOperation(symbol = "x")
    object Divide: CalculatorOperation(symbol = "/")
}

