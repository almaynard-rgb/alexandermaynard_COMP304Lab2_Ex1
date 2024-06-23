package com.example.alexandermaynard_comp304lab2_ex1

/* Student Name: Alexander Maynard
* Course: COMP304 (Section 401)
* Professor: Parth Padhiyar
* Assignment: Lab 2 - Exercise 1
* Date: 2024-06-22 */

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetachedHome : AppCompatActivity() {
    //list of detached homes to be used by the house items recycler view.
    private val detachedHomeList = ArrayList<String>()
    private var detachedHomeImageList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detached_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recycler list for the items to be added to the recycler view
        val detachedHomeRecyclerList = findViewById<RecyclerView>(R.id.house_type_recycler_view)

        //create instance of the HouseItemsRecyclerViewAdapter and assign it to the detachedHomeRecyclerAdapter
        detachedHomeRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, detachedHomeList, detachedHomeImageList)

        //add dividers for the recycler list items to more easily view them.
        detachedHomeRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the semi detached home recycler view
        detachedHomeRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add detached homes to the list of detached homes
        detachedHomeList.add("Detached Home: 3 bedroom, 3 bath. 65 Farmingdale Boulevard, Winnipeg NB, Canada at $400,800.")
        detachedHomeList.add("Detached Home: 2 bedroom, 2 bath. 1412 Grotto Avenue, Winnipeg NB, Canada at $365,000.")

        //from: https://www.quickenloans.com/learn/semi-detached-house
        detachedHomeImageList.add(R.drawable.detached_house_1)
        //from: https://www.galwaydaily.com/galway-property/three-bed-detached-house-comes-with-stunning-back-garden/
        detachedHomeImageList.add(R.drawable.detached_house_2)

        //detached homes checkout button functionality
        val detachedHomeCheckoutBtn = findViewById<Button>(R.id.detached_home_checkout_btn)
        //on click for the detached homes checkout button
        detachedHomeCheckoutBtn.setOnClickListener {
            //proceed to the checkout screen
            val nextActivityIntent = Intent(this@DetachedHome, CheckoutScreen::class.java)
            startActivity(nextActivityIntent)
        }
    }

    //inflate the options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    //provide options for when a options menu item is selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return menuItemSelected(item) //call method to provide the functionality
    }

    //method that provides functionality for all the options items when clicked
    private fun menuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //when apartment option is pressed
            R.id.apartment_page_menu_item -> {
                //go to the Apartment Screen
                val nextScreenIntent = Intent(this, Apartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            //when semi-detached home option is pressed
            R.id.semi_detached_home_page_menu_item -> {
                //go to the SemiDetachedHome Screen
                val nextScreenIntent = Intent(this, SemiDetachedHome::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            //when condominium apartment option is pressed
            R.id.condo_page_menu_item -> {
                //go to the CondominiumApartment Screen
                val nextScreenIntent = Intent(this, CondominiumApartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            //when townhouse home option is pressed
            R.id.townhouse_page_menu_item -> {
                //go to the Townhouse Screen
                val nextScreenIntent = Intent(this, Townhouse::class.java)
                startActivity(nextScreenIntent)
                return true
            }
        }
        return false
    }
}