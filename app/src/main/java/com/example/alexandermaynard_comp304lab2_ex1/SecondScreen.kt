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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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