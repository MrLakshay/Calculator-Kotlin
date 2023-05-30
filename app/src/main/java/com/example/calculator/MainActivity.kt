package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.substring
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvOp: TextView? = null

    var isDec=false
    var lastnumeric=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvOp=findViewById(R.id.tvop)
    }

    fun onDigit(view: View) {
        tvOp?.append((view as Button).text)
        lastnumeric=true
        isDec=false
    }

    fun onClear(view: android.view.View) {
        tvOp?.text=""
        isDec=false
    }
    fun onDec(view: android.view.View) {
        if(!isDec && lastnumeric){
            tvOp?.append(".")
            isDec=true
            lastnumeric=false
        }
    }

    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("-")
                    ||value.contains("+")
                    ||value.contains("*")
                    ||value.contains("/")
        }
    }
    fun onOperator(view: android.view.View) {
        tvOp?.text?.let{

            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvOp?.append((view as Button).text)
                lastnumeric=false
                isDec=false
            }
        }
    }

    fun onEqual(view: android.view.View) {
        if(lastnumeric){
            var tvValue=tvOp?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvOp?.text = printresult((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvOp?.text = printresult((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvOp?.text = printresult((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvOp?.text = printresult((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun printresult(value: String ):String {
        if(!value.endsWith("0")){
            return value
        }
        if(value.contains(".0")){
            return value.substring(0,value.length-2)
        }
        return value
    }

    fun onRemove(view: android.view.View) {
        var value=tvOp?.text?.toString()

        if(value!!.isNotEmpty()){
            tvOp?.text=value.substring(0,value.length-1)
        }
    }

}