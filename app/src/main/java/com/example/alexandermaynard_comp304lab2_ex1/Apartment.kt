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

class Apartment : AppCompatActivity() {
    //list of apartments to be used by the house items recycler view.
    private var apartmentList = ArrayList<String>()
    private var apartmentImageList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apartment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //recycler list for the items to be added to the recycler view
        val apartmentRecyclerList = findViewById<RecyclerView>(R.id.house_type_recycler_view)

        //create instance of the HouseItemsRecyclerViewAdapter and assign it to the apartmentRecyclerAdapter
        apartmentRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, apartmentList, apartmentImageList)

        //add dividers for the recycler list items to more easily view them.
        apartmentRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the apartment recycler view
        apartmentRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add apartments to the list of apartments
        apartmentList.add("Apartment: 2 bedroom, 1 bath. 16 Marion Street, Winnipeg NB, Canada at $3000/month.")
        apartmentList.add("Apartment: 1 bedroom, 1 bath. 24 Zander Street, Winnipeg MB, Canada at $1500/month.")

        //from: https://www.pexels.com/photo/interior-design-of-a-house-1571460/
        apartmentImageList.add(R.drawable.apartment_1)
        //from: https://www.pexels.com/photo/green-2-seat-sofa-1918291/
        apartmentImageList.add(R.drawable.apartment_2)

        //apartment checkout button functionality
        val apartmentCheckoutBtn = findViewById<Button>(R.id.apartment_checkout_btn)
        //on click for the apartment checkout button
        apartmentCheckoutBtn.setOnClickListener {
            //proceed to the checkout screen
            val nextActivityIntent = Intent(this@Apartment, CheckoutScreen::class.java)
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
        //check the item id
        when (item.itemId) {
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
            //when condo home option is pressed
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