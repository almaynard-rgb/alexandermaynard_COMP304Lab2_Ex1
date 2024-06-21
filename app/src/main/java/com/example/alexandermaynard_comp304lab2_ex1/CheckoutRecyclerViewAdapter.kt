package com.example.alexandermaynard_comp304lab2_ex1

import android.content.Context
import android.widget.RadioButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class CheckoutRecyclerViewAdapter (context: Context, private var houseChoiceRadioButton: List<RadioButton>, private var houseChoiceList: List<String>) :
    RecyclerView.Adapter<CheckoutRecyclerViewAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get radiobutton for each row
        var houseChoiceRadioButton: RadioButton = view.findViewById(R.id.checkout_row_radio_button)
        //get textview for each row
        var houseChoiceTextView: TextView = view.findViewById(R.id.checkout_row_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkout_recycler_view_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.houseChoiceRadioButton = houseChoiceRadioButton[position] //bind radio button item to the  proper row
        holder.houseChoiceTextView.text = houseChoiceList[position] //bind string item to the  proper row
    }
    override fun getItemCount(): Int {
        return houseChoiceRadioButton.size
    }
}