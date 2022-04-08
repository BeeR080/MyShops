package com.example.myshops.view.fragments.purchases

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshops.Adapters.ListOfPurchasesAdapter
import com.example.myshops.R
import com.example.myshops.data.purchases.PurchaseViewModel
import com.example.myshops.databinding.FragmentListBinding

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var mPurchasesViewModel: PurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater)
        binding.bottomNavMenu.selectedItemId = R.id.MyShops
       binding.bottomNavMenu.setOnNavigationItemSelectedListener {
           when(it.itemId){
               R.id.JointShops ->findNavController()
                   .navigate(R.id.action_listFragment_to_jointShopsFragment)
           }
           true
       }


        // RecyclerView
        val adapter = ListOfPurchasesAdapter()
        val recyclerView = binding.recyclerview
        val total = binding.total
        val totalcheked = binding.totalcheked
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Увеличения пула рецайкла для отображения вьюшек
        recyclerView.recycledViewPool
            .setMaxRecycledViews(ListOfPurchasesAdapter
                .VIEW_TYPE_CHEKED,
            ListOfPurchasesAdapter.MAX_POOL_SIZE)
        recyclerView.recycledViewPool
            .setMaxRecycledViews(ListOfPurchasesAdapter
                .VIEW_TYPE_NOTCHEKED,
            ListOfPurchasesAdapter.MAX_POOL_SIZE)

        // Удаление по свайпу
        val callback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.purchaseList[viewHolder.adapterPosition]
                mPurchasesViewModel.deletePurchase(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)



        //PurchasesViewModel
        mPurchasesViewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        mPurchasesViewModel.readAllData.observe(viewLifecycleOwner, Observer {  purchases ->
            adapter.setData(purchases)
             total.text = purchases.size.toString()

        })

        binding.addbutton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    // Верхний бар
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_menu_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // Верхний бар, нажатие на эелменты
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_ic -> deleteAllUsers()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить все покупки?")
        builder.setMessage("Вы уверены, что хотите удалить все покупки?")
        builder.setPositiveButton("Да"){ _, _ ->
            mPurchasesViewModel.deleteAllPurchases()
        }
        builder.setNegativeButton("Нет") { _, _ ->
        }
        builder.create().show()
    }


    }




