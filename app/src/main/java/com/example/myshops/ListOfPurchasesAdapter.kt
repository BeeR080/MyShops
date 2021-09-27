package com.example.myshops


import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myshops.data.PurchaseViewModel
import com.example.myshops.data.Purchases

import com.example.myshops.fragments.ListFragmentDirections

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.purchases_list.view.*



class ListOfPurchasesAdapter:RecyclerView.Adapter<ListOfPurchasesAdapter.MyViewHodler>() {
    private var purchaseList = emptyList<Purchases>()

    class MyViewHodler(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        return MyViewHodler(LayoutInflater.from(parent.context).inflate(R.layout.purchases_list, parent , false))

    }

    @SuppressLint("ResourceAsColor", "CutPasteId", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        val curentItem = purchaseList[position]
        holder.itemView.findViewById<TextView>(R.id.addname_rc).text = curentItem.purchaseName.toString()
        holder.itemView.findViewById<TextView>(R.id.adddesc_rc).text = curentItem.purchaseDesc.toString()
        holder.itemView.findViewById<TextView>(R.id.addcount_rc).text = "X:"+curentItem.purchaseCount.toInt().toString()
        holder.itemView.findViewById<CheckBox>(R.id.checkBox).isChecked = curentItem.checkbox
        holder.itemView.findViewById<ImageView>(R.id.addimage_rc).setImageResource(R.drawable.ic_marketshops)



        //Нажатие на элементы списка
        holder.itemView.listLayout.setOnLongClickListener {
            val actions = ListFragmentDirections.actionListFragmentToUpdateFragment(curentItem)
            holder.itemView.findNavController().navigate(actions)
            return@setOnLongClickListener true
        }
        val mPurchaseViewModel = PurchaseViewModel(application = Application())

        //Чекбокс
        val checkbox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        checkbox.setOnClickListener {
            if (checkbox.isChecked == true) {
                val updatePurchases = Purchases(
                        curentItem.id,
                        curentItem.purchaseName,
                        curentItem.purchaseDesc,
                        curentItem.purchaseCount,
                        true)
                mPurchaseViewModel.updatePurchase(updatePurchases)
               /*holder.itemView.listLayout.setBackgroundColor(Color.parseColor("#D3D2D2"))
                holder.itemView.addname_rc.setBackgroundColor(Color.parseColor("#A6A6A6"))
                holder.itemView.adddesc_rc.setBackgroundColor(Color.parseColor("#A6A6A6"))
                holder.itemView.addcount_rc.setBackgroundColor(Color.parseColor("#A6A6A6"))
                holder.itemView.addimage_rc.setColorFilter(Color.parseColor("#A6A6A6"))*/

            } else {
                val updatePurchases = Purchases(
                        curentItem.id,
                        curentItem.purchaseName,
                        curentItem.purchaseDesc,
                        curentItem.purchaseCount,
                        false)
                mPurchaseViewModel.updatePurchase(updatePurchases)
               /* holder.itemView.listLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                holder.itemView.adddesc_rc.setBackgroundResource(R.drawable.textview_border)
                holder.itemView.addname_rc.setBackgroundResource(R.drawable.textview_border)
                holder.itemView.addcount_rc.setBackgroundResource(R.drawable.textview_border)
                holder.itemView.addimage_rc.setColorFilter((Color.parseColor("#F4511E")))*/

            }


        }
    }

    override fun getItemCount(): Int {
        return purchaseList.size
    }
    fun setData(purchases:List<Purchases>){
        this.purchaseList = purchases
        notifyDataSetChanged()

    }
        fun getItemCounts():Int {
         return purchaseList.size
    }

    }







