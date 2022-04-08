package com.example.myshops.Adapters
import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myshops.R
import com.example.myshops.data.purchases.PurchaseViewModel
import com.example.myshops.data.Purchases
import com.example.myshops.view.fragments.purchases.ListFragmentDirections
import kotlinx.android.synthetic.main.purchases_list.view.*
import java.lang.RuntimeException


class ListOfPurchasesAdapter:RecyclerView.Adapter<ListOfPurchasesAdapter.MyViewHodler>() {
     var purchaseList = emptyList<Purchases>()


    class MyViewHodler(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        val layout = when(viewType){
            VIEW_TYPE_NOTCHEKED -> R.layout.purchases_list
            VIEW_TYPE_CHEKED-> R.layout.purchases_list_cheked
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
         return MyViewHodler(LayoutInflater.from(parent.context)
             .inflate(layout,
                 parent ,
                 false))


    }

    @SuppressLint("ResourceAsColor", "CutPasteId", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        val curentItem = purchaseList[position]
        holder.itemView.addname_rc.text = curentItem.purchaseName.toString()
        holder.itemView.adddesc_rc.text = curentItem.purchaseDesc.toString()
        holder.itemView.addcount_rc.text = "X:"+curentItem.purchaseCount.toInt().toString()
        holder.itemView.checkBox.isChecked = curentItem.checkbox
        holder.itemView.addimage_rc.setImageResource(R.drawable.ic_marketshops)

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

            } else {
                val updatePurchases = Purchases(
                        curentItem.id,
                        curentItem.purchaseName,
                        curentItem.purchaseDesc,
                        curentItem.purchaseCount,
                        false)
                mPurchaseViewModel.updatePurchase(updatePurchases)



            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        val item = purchaseList[position]
        return if (item.checkbox){
        VIEW_TYPE_CHEKED
        }else{
        VIEW_TYPE_NOTCHEKED
        }
        return super.getItemViewType(position)
    }


    override fun getItemCount(): Int {
        return purchaseList.size
    }
    fun setData(purchases:List<Purchases>){
        this.purchaseList = purchases
        notifyDataSetChanged()

    }


    companion object{
       const val VIEW_TYPE_CHEKED = 1
       const val VIEW_TYPE_NOTCHEKED = 0
        const val MAX_POOL_SIZE = 15
    }

    }







