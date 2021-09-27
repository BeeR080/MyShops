package com.example.myshops.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshops.ListOfPurchasesAdapter
import com.example.myshops.R
import com.example.myshops.data.PurchaseViewModel
import com.example.myshops.data.Purchases
import com.example.myshops.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.purchases_list.*

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var mPurchasesViewModel: PurchaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater)
        // RecyclerView
        val adapter = ListOfPurchasesAdapter()
        val recyclerView = binding.recyclerview
        val total = binding.total
        val totalcheked = binding.totalcheked
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        //PurchasesViewModel
        mPurchasesViewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        mPurchasesViewModel.readAllData.observe(viewLifecycleOwner, Observer { purchases ->
            adapter.setData(purchases)
            //total.text ="Товаров(куплено/всего):"+ " " + adapter.getItemCounts().toString()
             total.text = adapter.getItemCounts().toString()

        })
        mPurchasesViewModel.readCheked.observe(viewLifecycleOwner, Observer { checkBox->
            totalcheked.text = "Товаров(куплено/всего):"+ checkBox.size+ "/" + total.text
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




