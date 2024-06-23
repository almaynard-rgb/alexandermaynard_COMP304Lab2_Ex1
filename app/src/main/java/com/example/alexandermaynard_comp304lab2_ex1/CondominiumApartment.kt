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

class CondominiumApartment : AppCompatActivity() {
    //list of condo apartments to be used by the house items recycler view.
    private val condoApartmentList = ArrayList<String>()
    private var condoApartmentImageList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_condominium_apartment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //recycler list for the items to be added to the recycler view
        val condoApartmentRecyclerList = findViewById<RecyclerView>(R.id.house_type_recycler_view)

        //create instance of the HouseItemsRecyclerViewAdapter and assign it to the condoApartmentRecyclerAdapter
        condoApartmentRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, condoApartmentList, condoApartmentImageList)

        //add dividers for the recycler list items to more easily view them.
        condoApartmentRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the condo apartment recycler view
        condoApartmentRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add condo apartments to the list of condo apartments
        condoApartmentList.add("Condo: 2 bedroom, 2 bath. 1542 Pembina Highway, Winnipeg NB, Canada at $189,900")
        condoApartmentList.add("Condo: 3 bedroom, 2 bath. 2341 Creek Bend Road, Winnipeg NB, Canada at $339,900.")

        //from: https://e-rockwell.com/this-luxury-condo-in-quezon-city-is-proof-that-resort-style-living-can-be-yours-every-day/
        condoApartmentImageList.add(R.drawable.condo_1)
        //from: https://www.carousell.ph/p/1287832544/
        condoApartmentImageList.add(R.drawable.condo_2)

        //condo apartment checkout button functionality
        val condoApartmentCheckoutBtn = findViewById<Button>(R.id.condo_apartment_checkout_btn)
        //on click for the condo apartment checkout button
        condoApartmentCheckoutBtn.setOnClickListener {
            //proceed to the checkout screen
            val nextActivityIntent = Intent(this@CondominiumApartment, CheckoutScreen::class.java)
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
            //when detached home option is pressed
            R.id.detached_home_page_menu_item -> {
                //go to the DetachedHome Screen
                val nextScreenIntent = Intent(this, DetachedHome::class.java)
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