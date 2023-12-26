package com.TPSI_VFX.l_calculadorarodrigo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(actions: CalculatorActions) {
        when(actions) {
            is CalculatorActions.Number -> enterNumber(actions.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Operation -> enterOperation(actions.operation)
            is CalculatorActions.Calculate -> performCalc()
            is CalculatorActions.Delete -> performDel()


        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if(state.number1.length >= MAX_NUM_LENGHT) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGHT) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGHT = 8
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(",")
            && state.number1.isNotBlank()

        ) {
            state = state.copy(
                number1 = state.number1 + ","
            )
            return
        }

        if (!state.number2.contains(",") && state.number2.isNotBlank()

        ) {
            state = state.copy(
                number1 = state.number2 + ","
            )
        }

    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun performCalc() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null

            )
        }
    }

    private fun performDel() {
       when {
           state.number2.isNotBlank() -> state = state.copy(
               number2 = state.number2.dropLast(1)
           )
           state.operation != null -> state = state.copy(
               operation = null
           )
           state.number1.isNotBlank() -> state = state.copy(
               number1 = state.number1.dropLast(1)
           )
       }
    }

}