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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CheckoutScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        //shared preferences for the final house choice
        val finalHouseChoiceSharedPrefs = getSharedPreferences("finalHouseChoice", Context.MODE_PRIVATE)
        val editFinalHouseChoicePrefs = finalHouseChoiceSharedPrefs.edit() //used to edit the shared preferences more easily

        //preferences previously saved for which houses are preferred on the different house type screens
        val preferredHouseSharedPrefs = getSharedPreferences("housePreferences",0)

        //reference to the checkout radio group
        val checkoutHouseChoiceRadioGroup = findViewById<RadioGroup>(R.id.checkout_radio_group)
        checkoutHouseChoiceRadioGroup.clearCheck() //clear any checks before on start
        checkoutHouseChoiceRadioGroup.removeAllViews() //removeAllViews so there are no duplicates on start

        //loop through all the PREFERRED house shared preferences to create the necessary amount of radio buttons
        for (houseInfo in preferredHouseSharedPrefs.all)
        {
            //create a new radiobutton
            val newRadioButton = RadioButton(applicationContext)
            //desired density independent pixels
            val paddingDp = 20
            //get screen density
            val screenDensity = resources.displayMetrics.density
            //convert dp to actual pixels so the padding is correct
            val paddingDpToPx = paddingDp * screenDensity
            //set the padding to the items are spaced out properly
            newRadioButton.setPadding(0, paddingDpToPx.toInt(), 0 , paddingDpToPx.toInt())
            //set the text of the radio button text size
            newRadioButton.textSize = 16F
            //set the new radio button text to the shared preference
            newRadioButton.text = houseInfo.value.toString()
            checkoutHouseChoiceRadioGroup.addView(newRadioButton)

            //create setOnClickListener to listen for input on each radio button
            newRadioButton.setOnClickListener {
                //if this radio button is clicked then set it's text as the final house
                //selected shared preference (this may update or create it for the first time).
                editFinalHouseChoicePrefs.putString("finalHouseSelected", houseInfo.value.toString()).commit()
            }
        }

        //get button to go to the next activity
        val paymentBtn = findViewById<Button>(R.id.payment_checkout_btn)
        //to go next activity and pass the housetoBuy by intent
        paymentBtn.setOnClickListener {
            //check if at least one radio button is selected
            if(checkoutHouseChoiceRadioGroup.checkedRadioButtonId != -1) {
                //proceed to the next activity to pay
                val nextActivityIntent = Intent(this, PaymentScreen::class.java)
                startActivity(nextActivityIntent)
            }
            //payments fields not done properly
            else {
                //send a message to the user
                Toast.makeText(applicationContext, "You must selected a house to buy before proceeding!", Toast.LENGTH_LONG).show()
            }
        }
    }
}