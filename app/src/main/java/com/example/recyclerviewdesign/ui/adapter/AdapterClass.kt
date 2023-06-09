package com.example.recyclerviewdesign.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdesign.R
import com.example.recyclerviewdesign.data.Model

class AdapterClass(private val dataList:ArrayList<Model>):
    RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    //create onclick
    var onItemClick: ((Model)->Unit)? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

        //find item view that we want to add into recycler-view-layout in main-activity-layout
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolderClass(itemView);
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        //bind data into another item
        val currentItem = dataList[position];
        holder.rvImage.setImageResource(currentItem.dataImage);
        holder.rvTitle.text = currentItem.dataTitle;

        //set listener onclick to item
        holder.itemView.setOnClickListener{

            //check to know if current item was click
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {

        //count items size
        return dataList.size;
    }

    //find view in layout by id
    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView){
        val rvImage:ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.title)
    }


}