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

class Townhouse : AppCompatActivity() {
    //list of townhouses to be used by the recycler view.
    private val townhouseList = ArrayList<String>()
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
        townhouseRecyclerList.adapter = HouseItemsRecyclerViewAdapter(this, townhouseList)

        //add dividers for the recycler list items to more easily view them.
        townhouseRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        //create a vertical layout manager for the townhouse recycler view
        townhouseRecyclerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        //add townhouses to the list of townhouses
        townhouseList.add("Town House: 2 bedroom, 1 bath. 2131 Harbison Avenue, Winnipeg NB, Canada at $147,900")
        townhouseList.add("Town House: 3 bedroom, 2 bath. 1212 Corbett Drive, Winnipeg NB, Canada at $389,000.")

        //townhouse checkout button functionality
        val townhouseCheckoutBtn = findViewById<Button>(R.id.townhouse_checkout_btn)
        //on click for the townhouse checkout button
        townhouseCheckoutBtn.setOnClickListener {
            val nextActivityIntent = Intent(this@Townhouse, CheckoutScreen::class.java)
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
        }
        return false
    }
}