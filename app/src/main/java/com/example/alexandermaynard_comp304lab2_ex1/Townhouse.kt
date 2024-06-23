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

class Townhouse : AppCompatActivity() {
    //list of townhouses to be used by the house items recycler view.
    private val townhouseList = ArrayList<String>()
    private var townhouseImageList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_townhouse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recycler list for the items to be added to the recycler view
        val townhouseRecyclerList = findViewById<RecyclerView>(R.id.house_type_recycler_view)

        //create instance of the HouseItemsRecyclerViewAdapter and assign it to the townhouseRecyclerAdapter
        townhouseRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, townhouseList, townhouseImageList)

        //add dividers for the recycler list items to more easily view them.
        townhouseRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the townhouse recycler view
        townhouseRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add townhouses to the list of townhouses
        townhouseList.add("Town House: 2 bedroom, 1 bath. 2131 Harbison Avenue, Winnipeg NB, Canada at $147,900")
        townhouseList.add("Town House: 3 bedroom, 2 bath. 1212 Corbett Drive, Winnipeg NB, Canada at $389,000.")

        //from: https://www.houseful.ca/richmond-hill-on/2-amulet-crescent-richmond-hill-on-l4s-2s5/pid_upv9wvmh9q/
        townhouseImageList.add(R.drawable.town_house_1)
        //from: https://www.houseful.ca/hamilton-on/1540-upper-gage-ave-unit-7-hamilton-on-l8w-1e7/pid_rljiserx3y/
        townhouseImageList.add(R.drawable.town_house_2)


        //townhouse checkout button functionality
        val townhouseCheckoutBtn = findViewById<Button>(R.id.townhouse_checkout_btn)
        //on click for the townhouse checkout button
        townhouseCheckoutBtn.setOnClickListener {
            //proceed to the checkout screen
            val nextActivityIntent = Intent(this@Townhouse, CheckoutScreen::class.java)
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
            //when condominium apartment option is pressed
            R.id.condo_page_menu_item -> {
                //go to the CondominiumApartment Screen
                val nextScreenIntent = Intent(this, CondominiumApartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
        }
        return false
    }
}