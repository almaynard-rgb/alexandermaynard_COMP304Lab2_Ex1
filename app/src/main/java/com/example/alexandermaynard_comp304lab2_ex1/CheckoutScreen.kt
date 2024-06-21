package com.example.alexandermaynard_comp304lab2_ex1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckoutScreen : AppCompatActivity() {
    //house choice radio buttons list for Recycler view
    private val houseChoicesRadioButtonList = ArrayList<RadioButton>()
    //house choice string list for Recycler view
    private val houseChoicesList = ArrayList<String>()
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
        //preferences previously saved for which houses are preferred
        val houseSharedPrefs = getSharedPreferences("housePreferences",0)

        //initial house bought value
        var housetoBuy = ""

        //recycler list for the items to be added to the recycler view
        val recyclerList = findViewById<RecyclerView>(R.id.house_checkout_recycler_view)

        //add the recycler view to the adapter
        recyclerList.adapter = CheckoutRecyclerViewAdapter(this, houseChoicesRadioButtonList, houseChoicesList)

        //add dividers for the recycler list items
        recyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //vertical layout for the recycler items
        recyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)


        for (houseInfo in houseSharedPrefs.all)
        {
            if(houseChoicesList.contains(houseInfo.value.toString())) { return }
            val newRadioButton = RadioButton(applicationContext)
            houseChoicesRadioButtonList.add(newRadioButton)
            houseChoicesList.add(houseInfo.value.toString())
        }

        //get button to go to the next activity
        val checkoutBtn = findViewById<Button>(R.id.payment_checkout_btn)
        //to go next activity and pass the housetoBuy by intent
        checkoutBtn.setOnClickListener(View.OnClickListener {
            val nextActivityIntent = Intent(this, PaymentScreen::class.java)
            nextActivityIntent.putExtra("housetoBuy", housetoBuy)
            startActivity(nextActivityIntent)
        })
    }
}