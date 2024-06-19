package com.example.alexandermaynard_comp304lab2_ex1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class Apartment : AppCompatActivity() {

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


        val apartmentRecyclerList = findViewById<RecyclerView>(R.id.house_type_recycler_view)

        val apartmentRecyclerAdapter = HouseItemsRecyclerViewAdapter(this, apartmentList)
        apartmentRecyclerList.adapter = apartmentRecyclerAdapter

        //apartmentList.add()


        //add dividers for the recycler list items
        apartmentRecyclerList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        apartmentRecyclerList.layoutManager = layoutManager

        apartmentList.add("Apartment: 2 bedroom, 1 bath. 16 Marion Street, Winnipeg NB, Canada at $3000/month.")
        apartmentList.add("Apartment: 1 bedroom, 1 bath. 24 Zander Street, Winnipeg MB, Canada at $1500/month.")
    }
}