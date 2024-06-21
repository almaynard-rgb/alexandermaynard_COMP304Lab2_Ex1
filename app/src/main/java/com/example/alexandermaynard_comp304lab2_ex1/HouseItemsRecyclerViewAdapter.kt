package com.example.alexandermaynard_comp304lab2_ex1

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
    //shared preferences
    val houseSharedPrefs = context.getSharedPreferences("housePreferences", Context.MODE_PRIVATE)
    val editHousePrefs = houseSharedPrefs.edit()

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get the textview and checkbox for each row
        var houseTypeList: TextView = view.findViewById(R.id.house_type_row_textview)
        var houseTypeCheckBox: CheckBox = view.findViewById(R.id.house_type_row_checkbox)
        var houseImageAreaList: ImageView = view.findViewById(R.id.house_recycler_view_image)
    }

    //inflate view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.house_item_recycler_view_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val houseTypeText = houseTypeList[position]
        val houseTypeImage = houseImagePlaceholder[position]
        holder.houseTypeList.text = houseTypeText

        //check for if checkboxes are already saved
        if(houseSharedPrefs.contains("houseList$position${houseTypeList[position].substring(0,1)}")) {
            holder.houseTypeCheckBox.isChecked = true //if so, make sure the checkbox is selected
        }

        holder.houseImageAreaList.setImageResource(houseTypeImage)
        //create setOnCheckChangeListener for the checkboxes for input
        holder.houseTypeCheckBox
            .setOnCheckedChangeListener { _, isChecked ->
                //if they are checked then save preference the textview corresponding to the right checkbox
                if (isChecked) {
                    editHousePrefs.putString("houseList$position${houseTypeList[position].substring(0,1)}", houseTypeList[position]).commit()
                    //log the results for validation
                    houseSharedPrefs.getString("houseList$position${houseTypeList[position].substring(0,1)}", "")
                        ?.let { Log.d("CHECKED", it) }
                }
                //if they are not checked then remove the preference for the textview corresponding to the right checkbox
                else {
                    editHousePrefs.remove("houseList$position${houseTypeList[position].substring(0,1)}").commit()
                    //log the results for validation
                    houseSharedPrefs.getString("houseList$position${houseTypeList[position].substring(0,1)}", "")
                        ?.let { Log.d("NOT-CHECKED", it) }
                }
            }
    }
    override fun getItemCount(): Int {
        return houseTypeList.size
    }
}