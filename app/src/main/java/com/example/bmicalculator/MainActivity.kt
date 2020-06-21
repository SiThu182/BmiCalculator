package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        spinner_height.adapter = ArrayAdapter.createFromResource(this,R.array.heightUnits, android.R.layout.simple_spinner_item)
        spinner_weight.adapter = ArrayAdapter.createFromResource(this,R.array.weightUnits,android.R.layout.simple_spinner_item)

        var heightUnit  = ""
        var weightUnit = ""

        spinner_height.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                heightUnit = ""
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                 heightUnit = parent?.adapter?.getItem(position).toString()
                Log.d("BMI","Select weight $position")
            }

        }

        spinner_weight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                weightUnit = ""
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                weightUnit = parent?.adapter?.getItem(position).toString()
                Log.d("BMI","Select Height $weightUnit")
            }

        }

        calculator.setOnClickListener{
           val heighValue = if(heightUnit == "M") height.text.toString().toDouble() else height.text.toString().toDouble() * 0.3048
           val weightValue = if (weightUnit == "kg") weight.text.toString().toDouble() else weight.text.toString().toDouble() * 0.453592
            Log.d("bmi" ,"$heighValue & $weightValue")
            val bmi = weightValue / (heighValue * heighValue)
            value.text = bmi.toString()

            result.text = if( bmi < 1.5){
                "thinness"
            }else if(bmi < 25){
                "Normal"
            }else{
                "obesity"
            }
        }
    }
}