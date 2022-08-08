package com.example.vatapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn.setOnClickListener {
            calculateVat()
        }

    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateVat() {
        val stringInTextField = binding.etTotal.text.toString()
        val cost = stringInTextField.toDouble()

        val selected = binding.percent.checkedRadioButtonId
        val vatPercentage = when (selected){

            R.id.percent_10 -> 0.10
            R.id.percent_15 -> 0.15
            else -> 0.20
        }


        var vat =  vatPercentage * cost
        var total =  cost + vat

        val roundVat = binding.switchRoundUp.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
       binding.txtTotal.text = getString(R.string.totals,formattedTotal)
        val formattedVat = NumberFormat.getCurrencyInstance().format(vat)

        binding.txtVatTotal.text = getString(R.string.vats, formattedVat)
        val formattedCost = NumberFormat.getCurrencyInstance().format(cost)

        binding.txtCostTotal.text = getString(R.string.cost, formattedCost)

    }


    }
