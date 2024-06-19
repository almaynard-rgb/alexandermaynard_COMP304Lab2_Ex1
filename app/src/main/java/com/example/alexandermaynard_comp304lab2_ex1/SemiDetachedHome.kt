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

class SemiDetachedHome : AppCompatActivity() {
    //list of semi-detached homes to be used by the recycler view.
    private val semiDetachedHomeList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_semi_detached_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recycler list for the items to be added to the recycler view
        val detachedHomeRecyclerList = findViewById<RecyclerView>(R.id.house_type_recycler_view)

        //create instance of the HouseItemsRecyclerViewAdapter and assign it to the semiDetachedHomeRecyclerAdapter
        detachedHomeRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, semiDetachedHomeList)

        //add dividers for the recycler list items to more easily view them.
        detachedHomeRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the semi-detached home recycler view
        detachedHomeRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add semi-detached homes to the list of detached homes
        semiDetachedHomeList.add("Semi-Detached Home: 2 bedroom, 1 bath. 1231 Brandon Street, Winnipeg NB, Canada at $320,000.")
        semiDetachedHomeList.add("Semi-Detached Home: 3 bedroom, 2 bath. 453 St. Malo Boulevard, Winnipeg NB, Canada at $460,650.")


        //semi-detached homes checkout button functionality
        val detachedHomeCheckoutBtn = findViewById<Button>(R.id.semi_detached_home_checkout_btn)
        //on click for the semi-detached homes checkout button
        detachedHomeCheckoutBtn.setOnClickListener {
            val nextActivityIntent = Intent(this@SemiDetachedHome, CheckoutScreen::class.java)
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
            R.id.apartment_page_menu_item -> {
                val nextScreenIntent = Intent(this, Apartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            R.id.detached_home_page_menu_item -> {
                val nextScreenIntent = Intent(this, DetachedHome::class.java)
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