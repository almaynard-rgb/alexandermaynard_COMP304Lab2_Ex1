package com.example.alexandermaynard_comp304lab2_ex1

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

        //shared preferences
        val finalHouseChoiceSharedPrefs = getSharedPreferences("finalHouseChoice", Context.MODE_PRIVATE)
        val editFinalHouseChoicePrefs = finalHouseChoiceSharedPrefs.edit()

        //preferences previously saved for which houses are preferred
        val houseSharedPrefs = getSharedPreferences("housePreferences",0)

        val checkoutHouseChoiceRadioGroup = findViewById<RadioGroup>(R.id.checkout_radio_group)
        checkoutHouseChoiceRadioGroup.clearCheck()
        checkoutHouseChoiceRadioGroup.removeAllViews()

        for (houseInfo in houseSharedPrefs.all)
        {
            //if(houseChoicesList.contains(houseInfo.value.toString())) { return }
            val newRadioButton = RadioButton(applicationContext)
            val paddingDp = 20
            val screenDensity = resources.displayMetrics.density
            val paddingDpToPx = paddingDp * screenDensity
            newRadioButton.setPadding(0, paddingDpToPx.toInt(), 0 , paddingDpToPx.toInt())
            newRadioButton.textSize = 16F
            newRadioButton.text = houseInfo.value.toString()
            checkoutHouseChoiceRadioGroup.addView(newRadioButton)

            newRadioButton.setOnClickListener {
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