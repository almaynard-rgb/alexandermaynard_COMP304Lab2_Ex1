package com.example.alexandermaynard_comp304lab2_ex1

/* Student Name: Alexander Maynard
* Course: COMP304 (Section 401)
* Professor: Parth Padhiyar
* Assignment: Lab 2 - Exercise 1
* Date: 2024-06-22 */

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinalOrderInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_final_order_information)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        //set the text for the option picked to rental or house option from the checkout intent
        val finalOrderInfoTextview = findViewById<TextView>(R.id.final_order_info_textview)
        finalOrderInfoTextview.text = getSharedPreferences("finalHouseChoice", Context.MODE_PRIVATE).getString("finalHouseSelected", "")

        val mainScreenBtn = findViewById<Button>(R.id.main_enter_btn)
        mainScreenBtn.setOnClickListener {
            val i = Intent(this@FinalOrderInformation, MainScreen::class.java)
            startActivity(i)
        }
    }
}