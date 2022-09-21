package com.example.lab1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submit = findViewById(R.id.submit) as Button
        val reset = findViewById(R.id.reset) as ImageButton
        val mainText = findViewById(R.id.inputText) as TextView
        val substring = findViewById(R.id.substringText) as TextView
        val result = findViewById(R.id.result) as TextView

        submit.setOnClickListener {
            val words = mainText.text.toString().split(" ".toRegex()).toTypedArray()
            if(mainText.text.toString() != "" && substring.text.toString() != ""){
                result.text = "Result: " + words.filter { e -> e.contains(substring.text.toString().toRegex()) }.count().toString()
            } else {
                result.text = ""
                Toast.makeText(this@MainActivity, "Please, enter the required information", Toast.LENGTH_SHORT).show()
            }
            closeKeyBoard()
        }

        reset.setOnClickListener {
            mainText.text = ""
            substring.text = ""
            result.text = ""
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}