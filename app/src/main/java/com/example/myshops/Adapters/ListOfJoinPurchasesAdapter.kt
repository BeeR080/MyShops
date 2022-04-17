package com.example.myshops.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myshops.R
import com.example.myshops.data.jointpurchases.JointPurchases
import com.example.myshops.view.fragments.jointpurchases.JointShopsFragmentDirections
import com.example.myshops.view.fragments.purchases.ListFragmentDirections
import kotlinx.android.synthetic.main.jointpurchases_list.view.*

class ListOfJoinPurchasesAdapter: RecyclerView.Adapter<ListOfJoinPurchasesAdapter.JointListViewHolder>() {

    var purchasesList = ArrayList<JointPurchases>()

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
        val currentItem = purchasesList[position]
        holder.itemView.tv_jointshops_name.text = currentItem.name
        holder.itemView.tv_jointshops_description.text = currentItem.desc
        holder.itemView.tv_jointshops_count.text = "X:"+ currentItem.count.toString()
        holder.itemView.checkBox_jointpurchases.isChecked = currentItem.chekbox
        holder.itemView.img_jointshops_image.setImageResource(R.drawable.ic_marketshops)


        holder.itemView.setOnClickListener {
            val actions = JointShopsFragmentDirections
                .actionJointShopsFragmentToJointShopsEditFragment(currentItem)
            holder.itemView.findNavController().navigate(actions)
        }
    }

    override fun getItemCount(): Int {
       return purchasesList.size

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(purchases: ArrayList<JointPurchases>){
        this.purchasesList = purchases
        notifyDataSetChanged()
    }










}