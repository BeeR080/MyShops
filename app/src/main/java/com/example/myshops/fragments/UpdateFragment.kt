package com.example.myshops.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshops.R
import com.example.myshops.data.PurchaseViewModel
import com.example.myshops.data.Purchases
import com.example.myshops.databinding.FragmentUpdateBinding
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.textdesc


class UpdateFragment : Fragment() {
    lateinit var binding: FragmentUpdateBinding
    private lateinit var  mPurchasesViewModel: PurchaseViewModel
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater)
        mPurchasesViewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        binding.updatename.setText(args.currentPurchases.purchaseName)
        binding.updatedesc.setText(args.currentPurchases.purchaseDesc)
        binding.updatecount.setText(args.currentPurchases.purchaseCount.toString())


        setHasOptionsMenu(true)


        //Кнопка "+"
        binding.plusbtn.setOnClickListener(){
            var countedit = updatecount
            var x = updatecount.text.toString().toInt()
            var count = x+1
            countedit.setText(count.toString())
        }
        // Кнока "-"
        binding.minusbtn.setOnClickListener(){
            var countedit = updatecount
            var x = updatecount.text.toString().toInt()
            var count = x - 1
            if (count < 0) {
                countedit.setText("0").toString()
            } else {
                countedit.setText(count.toString())
            }
        }

        return binding.root
    }


    private fun updateItem(){
        val name = updatename.text.toString()
        val desc = updatedesc.text.toString()
        val count = updatecount.text.toString().toInt()


        if(inputChek(name)){
            val updatePurchase = Purchases(args.currentPurchases.id,name,desc,count, checkbox =false)
            mPurchasesViewModel.updatePurchase(updatePurchase)
            Toast.makeText(requireContext(), "Данные изменены", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Поле Имя не должно быть пустым", Toast.LENGTH_SHORT).show()
        }

    }
    private fun inputChek(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }
// Верхний бар
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }
    // Верхний бар, нажатие на эелменты
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_ic -> deletePurchase()
            R.id.save_ic -> updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePurchase() {
        mPurchasesViewModel.deletePurchase(args.currentPurchases)
        Toast.makeText(requireContext(),
                "Продукт ${args.currentPurchases.purchaseName} удален",
                Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

    }





}

