package com.example.calculadorimc

import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import java.nio.channels.UnsupportedAddressTypeException
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CalculadorIMC)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val npWeight = findViewById<NumberPicker>(R.id.npWeight)

        val btCalculate = findViewById<Button>(R.id.btCalculate)


        val sbHeight = findViewById<SeekBar>(R.id.sbHeight)
        val tvHeightSb = findViewById<TextView>(R.id.tvHeightSb)

        sbHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tvHeightSb.setText(sbHeight.getProgress().toString() + " cm ")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        numberPikers()
        btCalculate.setOnClickListener {
            val weight = npWeight.value.toFloat()
            val height = sbHeight.getProgress().toFloat()
            val imc = calcularIMC(weight, height)
            Log.i("addas","$weight  $height")
            showCustomDialogBox(category(imc),imc)

        }

    }

    private fun showCustomDialogBox(message: String , imc:Float) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_result)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvCategory = dialog.findViewById<TextView>(R.id.tvCategory)
        val tvIMC = dialog.findViewById<TextView>(R.id.tvIMC)
        val btAccept = dialog.findViewById<Button>(R.id.btAccept)

        tvIMC.setText(imc.toString())
        tvCategory.setText("$message")
        btAccept.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun numberPikers() {
        val npWeight = findViewById<NumberPicker>(R.id.npWeight)
        val npAge = findViewById<NumberPicker>(R.id.npAge)
        //Weight
        npWeight.setMaxValue(200)
        npWeight.setMinValue(10)
        npWeight.setValue(80)
        //Age
        npAge.setMaxValue(120)
        npAge.setMinValue(1)
        npAge.setValue(25)
    }

    fun calcularIMC(weight: Float, height: Float): Float {
        return (weight / ((height / 100) * (height / 100)))
    }

    fun category(imc: Float): String {
        return when {
            imc < 18.5 -> resources.getString(R.string.category_underweight)
            imc < 24.9 -> resources.getString(R.string.category_normal_weight)
            imc < 29.9 -> resources.getString(R.string.category_overweight)
            imc < 34.9 -> resources.getString(R.string.category_obesity_type_1)
            imc < 39.9 -> resources.getString(R.string.category_obesity_type_2)
            else -> resources.getString(R.string.category_obesity_type_3)
        }

    }
}
