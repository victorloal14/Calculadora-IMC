package com.example.calculadorimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)
        val resultado: Float? = intent.extras?.getFloat("EtraIMC")

    }

    fun calcularIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }

    fun obtenerResultadoIMC(imc: Double): String {
        return when {
            imc < 16 -> "Infrapeso severo"
            imc < 17 -> "Infrapeso moderado"
            imc < 18.5 -> "Infrapeso leve"
            imc < 24.9 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidad tipo I"
            imc < 39.9 -> "Obesidad tipo II"
            else -> "Obesidad tipo III (mórbida)"
        }
    }


}