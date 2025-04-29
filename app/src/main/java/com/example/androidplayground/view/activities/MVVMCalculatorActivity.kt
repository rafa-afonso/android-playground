package com.example.androidplayground.view.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.example.androidplayground.databinding.ActivityMvvmCalculatorBinding
import com.example.androidplayground.viewmodel.CalculatorViewModel

class MVVMCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmCalculatorBinding
    private lateinit var calculatorViewModel: CalculatorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

        val calculatorButton = binding.btnCalculate

        calculatorButton.setOnClickListener {
            val num1 = binding.etFirstValue.text.toString().toIntOrNull() ?: 0
            val num2 = binding.etSecondValue.text.toString().toIntOrNull() ?: 0

            val result = calculatorViewModel.calculateSum(num1, num2)

            val tvResult = binding.tvResult
            tvResult.text = "${result.sum}"

            val resultView = binding.resultView
            resultView.visibility = View.VISIBLE
        }
    }

    private fun setup() {
        enableEdgeToEdge()
        binding = ActivityMvvmCalculatorBinding.inflate(layoutInflater)
        val mainView = binding.root
        setContentView(mainView)

        calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        val originalPaddingLeft = mainView.paddingLeft
        val originalPaddingTop = mainView.paddingTop
        val originalPaddingRight = mainView.paddingRight
        val originalPaddingBottom = mainView.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.updatePadding(
                left = originalPaddingLeft + systemBars.left,
                top = originalPaddingTop + systemBars.top,
                right = originalPaddingRight + systemBars.right,
                bottom = originalPaddingBottom + systemBars.bottom
            )
            insets
        }
    }
}