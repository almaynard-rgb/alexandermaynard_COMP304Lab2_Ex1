package com.example.alexandermaynard_comp304lab2_ex1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class HouseItemsRecyclerViewAdapter(context: Context, private var houseTypeList: List<String>, private var houseImagePlaceholder: ArrayList<Int>) :
    RecyclerView.Adapter<HouseItemsRecyclerViewAdapter.MyViewHolder>() {

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
        holder.houseImageAreaList.setImageResource(houseTypeImage)
        //check if the checkboxes are checked or not
    }
    override fun getItemCount(): Int {
        return houseTypeList.size
    }
}