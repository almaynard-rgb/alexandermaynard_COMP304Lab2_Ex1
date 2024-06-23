package com.example.alexandermaynard_comp304lab2_ex1

/* Student Name: Alexander Maynard
* Course: COMP304 (Section 401)
* Professor: Parth Padhiyar
* Assignment: Lab 2 - Exercise 1
* Date: 2024-06-22 */

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class HouseItemsRecyclerViewAdapter(context: Context, private var houseTypeList: List<String>, private var houseImagePlaceholder: ArrayList<Int>) :
    RecyclerView.Adapter<HouseItemsRecyclerViewAdapter.MyViewHolder>() {
    //shared preferences for the houses that were selected from the home type pages
    private val houseSharedPrefs = context.getSharedPreferences("housePreferences", Context.MODE_PRIVATE)
    private val editHousePrefs = houseSharedPrefs.edit() //used to edit the preferences from easily

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get the textview, checkbox and image view for each row
        var houseTypeList: TextView = view.findViewById(R.id.house_type_row_textview)
        var houseTypeCheckBox: CheckBox = view.findViewById(R.id.house_type_row_checkbox)
        var houseImageAreaList: ImageView = view.findViewById(R.id.house_recycler_view_image)
    }

    //inflate view holder using the house_item_recycler_view_row.xml file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.house_item_recycler_view_row, parent, false)
        return MyViewHolder(itemView)
    }

    //bind the data to the items in each row that is created using the house_item_recycler_view_row.xml file
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.houseTypeList.text = houseTypeList[position] //assigning each houseTypeList.text for each row
        holder.houseImageAreaList.setImageResource(houseImagePlaceholder[position]) //assigning each houseTypeImage for each row

        //check for if checkboxes are already saved by looking at each unique saved preference
        if(houseSharedPrefs.contains("houseList$position${houseTypeList[position].substring(0,1)}")) {
            holder.houseTypeCheckBox.isChecked = true //if so, make sure the checkbox is selected
        }

        //create setOnCheckChangeListener for the checkboxes for input
        holder.houseTypeCheckBox
            .setOnCheckedChangeListener { _, isChecked ->
                //if they are checked then save preference the textview corresponding to the right checkbox
                if (isChecked) {
                    //update or insert new saved preference unique to each house row item
                    editHousePrefs.putString("houseList$position${houseTypeList[position].substring(0,1)}", houseTypeList[position]).commit()
                    //log the results for validation
                    houseSharedPrefs.getString("houseList$position${houseTypeList[position].substring(0,1)}", "")
                        ?.let { Log.d("CHECKED", it) }
                }
                //if they are not checked then remove the preference for the textview corresponding to the right checkbox
                else {
                    //remove saved preference unique to each house row item
                    editHousePrefs.remove("houseList$position${houseTypeList[position].substring(0,1)}").commit()
                    //log the results for validation
                    houseSharedPrefs.getString("houseList$position${houseTypeList[position].substring(0,1)}", "")
                        ?.let { Log.d("NOT-CHECKED", it) }
                }
            }
    }
    //get the size of the houseTypeList for later use if needed
    override fun getItemCount(): Int {
        return houseTypeList.size
    }
}