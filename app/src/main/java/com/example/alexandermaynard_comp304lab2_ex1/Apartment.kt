package com.example.alexandermaynard_comp304lab2_ex1

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
import kotlin.collections.ArrayList

class Apartment : AppCompatActivity() {
    //list of apartments to be used by the recycler view.
    private var apartmentList = ArrayList<String>()

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
        apartmentRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, apartmentList)

        //add dividers for the recycler list items to more easily view them.
        apartmentRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the apartment recycler view
        apartmentRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add apartments to the list of apartments
        apartmentList.add("Apartment: 2 bedroom, 1 bath. 16 Marion Street, Winnipeg NB, Canada at $3000/month.")
        apartmentList.add("Apartment: 1 bedroom, 1 bath. 24 Zander Street, Winnipeg MB, Canada at $1500/month.")

        //apartment checkout button functionality
        val apartmentCheckoutBtn = findViewById<Button>(R.id.apartment_checkout_btn)
        //on click for the apartment checkout button
        apartmentCheckoutBtn.setOnClickListener {
            val nextActivityIntent = Intent(this@Apartment, CheckoutScreen::class.java)
            startActivity(nextActivityIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return menuItemSelected(item)
    }

    private fun menuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.detached_home_page_menu_item -> {
                val nextScreenIntent = Intent(this, DetachedHome::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            R.id.semi_detached_home_page_menu_item -> {
                val nextScreenIntent = Intent(this, SemiDetachedHome::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            R.id.condo_page_menu_item -> {
                val nextScreenIntent = Intent(this, CondominiumApartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            R.id.townhouse_page_menu_item -> {
                val nextScreenIntent = Intent(this, Townhouse::class.java)
                startActivity(nextScreenIntent)
                return true
            }
        }
        return false
    }
}