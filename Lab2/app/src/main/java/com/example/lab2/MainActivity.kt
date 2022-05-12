package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var inputTV: TextView
    private var temp = StringBuilder()
    private var inputNum = StringBuilder()
    private var curInput = StringBuilder()
    private lateinit var numberButtons: Array<Button>
    private lateinit var operationButtons: Array<Button>
    private var operator: Operator = Operator.NONE
    private var isOperationClicked: Boolean = false
    private var fArg: Int = 0
    private var secArg: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAll()
    }

    private fun initAll() {
        inputTV = findViewById(R.id.input)
        numberButtons = arrayOf(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine)
        operationButtons = arrayOf(btnPlus, btnMinus, btnMult, btnDiv)
        for (i in numberButtons) { i.setOnClickListener { numberButtonClick(i) } }
        for (i in operationButtons) { i.setOnClickListener { operationButtonClick(i) } }
        btnEqual.setOnClickListener { equalButtonClick() }
        btnDel.setOnClickListener { delLast() }
    }

    private fun delLast() {
        curInput.setLength(curInput.length - 1)
        input.text = curInput
    }

    private fun equalButtonClick() {

        val result = when(operator) {
            Operator.PLUS -> fArg + secArg
            Operator.MINUS -> fArg - secArg
            Operator.MULTIPLICATION -> fArg * secArg
            Operator.DIVISION -> fArg / secArg
            else -> 0
        }

        inputNum.clear()
        inputNum.append(result)
        output.text = inputNum
        inputNum.clear()
        temp.clear()
    }

    private fun numberButtonClick(btn: Button) {
        if (isOperationClicked) {
            inputNum.append(temp)
            temp.clear()
            temp.append(btn.text)
            secArg = temp.toString().toInt()
            inputNum.append(" $temp")
            inputTV.text = inputNum
            curInput = inputNum
            isOperationClicked = false
        }
        else {
            inputNum.clear()
            inputNum.append(btn.text)
            inputTV.text = inputNum
            curInput = inputNum
        }
    }

    private fun operationButtonClick(btn: Button) {
        if (!isOperationClicked) {

            fArg = inputNum.toString().toInt()

            when (btn.text) {
                "+" -> {
                    operator = Operator.PLUS
                    inputNum.append(" + ")
                    inputTV.text = inputNum
                    temp.append(inputNum)
                    curInput = inputNum
                    inputNum.clear()
                }
                "-" -> {
                    operator = Operator.MINUS
                    inputNum.append(" - ")
                    inputTV.text = inputNum
                    temp.append(inputNum)
                    curInput = inputNum
                    inputNum.clear()
                }
                "*" -> {
                    operator = Operator.MULTIPLICATION
                    inputNum.append(" * ")
                    inputTV.text = inputNum
                    temp.append(inputNum)
                    curInput = inputNum
                    inputNum.clear()
                }
                "/" -> {
                    operator = Operator.DIVISION
                    inputNum.append(" / ")
                    inputTV.text = inputNum
                    temp.append(inputNum)
                    curInput = inputNum
                    inputNum.clear()
                }
                else -> operator = Operator.NONE
            }
        }

        isOperationClicked = true
    }
}

enum class Operator {PLUS, MINUS, MULTIPLICATION, DIVISION, NONE}