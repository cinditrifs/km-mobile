package com.cindi.user_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class FirstActivity : AppCompatActivity() {
    private lateinit var next : Button
    private lateinit var check :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        //next button
        val input_name = findViewById<TextInputEditText>(R.id.fullName)
        next = findViewById<Button>(R.id.nextSecond)
        next.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            val message = input_name.text.toString()
            intent.putExtra("full_name", message)
            startActivity(intent)
        }

        //check palindrom button
        val input_palli = findViewById<TextInputEditText>(R.id.inputPalindrome)
        check = findViewById<Button>(R.id.buttonCheck)
        check.setOnClickListener {
            val palindrome = input_palli.text.toString()
            var reverseString = ""
            var length = palindrome.length

            for (i in (length - 1) downTo 0) {
                reverseString = reverseString + palindrome[i]
            }

            if (palindrome == "" ){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Palindrome Result")
                builder.setMessage("Fill the palindrom field")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }
            else {
                if (palindrome.equals(reverseString,ignoreCase = true)) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Palindrome Result")
                    builder.setMessage("\"" + palindrome + "\""+ " isPalindrome")

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        Toast.makeText(applicationContext,
                            android.R.string.yes, Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Palindrome Result")
                    builder.setMessage("\"" + palindrome + "\""+ " not Palindrome")

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        Toast.makeText(applicationContext,
                            android.R.string.yes, Toast.LENGTH_SHORT).show()
                    }
                    builder.show() }
            }}
        }
    }
