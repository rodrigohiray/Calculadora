package com.TPSI_VFX.l_calculadorarodrigo

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)
