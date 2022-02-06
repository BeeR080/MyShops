package com.example.myshops.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshops.R
import com.example.myshops.data.JointPurchases
import kotlinx.android.synthetic.main.jointpurchases_list.view.*

class ListOfJoinPurchasesAdapter: RecyclerView.Adapter<ListOfJoinPurchasesAdapter.JointListViewHolder>() {

    var purchases = emptyList<JointPurchases>()

    class JointListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JointListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.jointpurchases_list,
                parent,
                false)

        return JointListViewHolder(view)
    }


    override fun onBindViewHolder(holder: JointListViewHolder, position: Int) {
        val currentItem = purchases[position]
        holder.itemView.tv_jointshops_name.text = currentItem.name
        holder.itemView.tv_jointshops_description.text = currentItem.desc
        holder.itemView.tv_jointshops_count.text = "X:"+ currentItem.count.toString()
        holder.itemView.checkBox_jointpurchases.isChecked = currentItem.chekbox
        holder.itemView.img_jointshops_image.setImageResource(R.drawable.ic_marketshops)


    }

    override fun getItemCount(): Int {
       return purchases.size

    }

    fun setData(purchases: List<JointPurchases>){
        this.purchases = purchases
        notifyDataSetChanged()
    }




}