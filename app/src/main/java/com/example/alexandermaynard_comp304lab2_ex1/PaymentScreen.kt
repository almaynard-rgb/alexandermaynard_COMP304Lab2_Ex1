package com.example.alexandermaynard_comp304lab2_ex1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/* Student Name: Alexander Maynard
* Course: COMP304 (Section 401)
* Professor: Parth Padhiyar
* Assignment: Lab 2 - Exercise 1
* Date: 2024-06-22 */

class PaymentScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ///name and credit card fields from the xml file
        val fullnameTxt = findViewById<EditText>(R.id.payment_full_name_entry).text
        val creditCardTxt = findViewById<EditText>(R.id.payment_credit_or_debit_entry).text

        //set the text for the option picked to rental or house option from the checkout intent
        val paymentHouseOptionChoice = findViewById<TextView>(R.id.payment_house_option_choice)
        paymentHouseOptionChoice.text = getSharedPreferences("finalHouseChoice", Context.MODE_PRIVATE).getString("finalHouseSelected", "")

        //pay btn
        val payBtn = findViewById<Button>(R.id.pay_btn)
        //onclick check if fields are all filled in and if the user has an item selected to pay for
        payBtn.setOnClickListener {
            //if any mandatory field is either null or credit/debit card isn't at least 16 digits or less than 19 digits then payment declined
            if(fullnameTxt.toString() == "" || paymentHouseOptionChoice.text == "" || creditCardTxt.length < 16 || creditCardTxt.length > 19) {
                Toast.makeText(applicationContext, "Payment Declined!", Toast.LENGTH_LONG).show()
            }
            //otherwise, you payed successfully
            else {
                //send success message and then return to the main screen
                Toast.makeText(applicationContext, "Payment Successful!", Toast.LENGTH_LONG).show()
                val nextActivityIntent = Intent(this, MainScreen::class.java)
                startActivity(nextActivityIntent)
            }
        }
    }
}