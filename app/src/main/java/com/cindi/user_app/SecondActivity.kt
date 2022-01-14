package com.cindi.user_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var full_name: String
    private lateinit var back : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //back to first screen
        back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            finish()
        }

        //passing data from first screen
        full_name = intent.getStringExtra("full_name").toString()
        findViewById<TextView>(R.id.secondfullname).text = full_name

        //passting data from third activity
        var choose_name = intent.getStringExtra("choose_name").toString()
        if (choose_name == "null"){
            choose_name = "Selected User Name"
        }
        findViewById<TextView>(R.id.selectedUserName).text = choose_name
    }

    fun chooseName(view: View) {
        val intent = Intent(this, ThirdActivity::class.java)
        intent.putExtra("full_name", full_name)
        startActivity(intent)
    }
}