package com.example.myshops.view.fragments.jointpurchases

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Database
import com.example.myshops.R
import com.example.myshops.data.Purchases
import com.example.myshops.data.jointpurchases.ApiFireBase
import com.example.myshops.data.jointpurchases.JointPurchases
import com.example.myshops.data.jointpurchases.JointPurchasesViewModel
import com.example.myshops.databinding.FragmentJointShopsEditBinding
import com.example.myshops.view.fragments.purchases.UpdateFragmentArgs
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_joint_shops_edit.*
import kotlinx.android.synthetic.main.fragment_update.*


class JointShopsEditFragment : Fragment() {

    private lateinit var binding: FragmentJointShopsEditBinding
    private lateinit var jPurchasesViewModel: JointPurchasesViewModel
    private val args by navArgs<JointShopsEditFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJointShopsEditBinding.inflate(inflater)
        jPurchasesViewModel = ViewModelProvider(this).get(JointPurchasesViewModel::class.java)

        fbEditData()

        setHasOptionsMenu(true)


        //Кнопка "+"
        binding.dialogEditPlusbtn.setOnClickListener(){
            var countedit = dialog_edit_addcount
            var x = countedit.text.toString().toInt()
            var count = x+1
            countedit.setText(count.toString())
        }
        // Кнока "-"
        binding.dialogEditMinusbtn.setOnClickListener(){
            var countedit = dialog_edit_addcount
            var x = countedit.text.toString().toInt()
            var count = x - 1
            if (count < 0) {
                countedit.setText("0").toString()
            } else {
                countedit.setText(count.toString())
            }
        }

        return binding.root




    }
    // Верхний бар
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }
    // Верхний бар, нажатие на эелменты
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_ic -> deletePurchases()
            R.id.save_ic -> updatePurchase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePurchases() {
        val purchase = args.currentPurchases
        jPurchasesViewModel.deleteDataOnFB(purchase)
        findNavController().popBackStack()
    }

    private fun inputChek(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }
    private fun updatePurchase() {
        val name = dialog_edit_addname.text.toString()
        val desc = dialog_edit_adddesc.text.toString()
        val count = dialog_edit_addcount.text.toString().toInt()
        if(inputChek(name)){
            //Удаляем старую запись
            jPurchasesViewModel.deleteDataOnFB(args.currentPurchases)
            val editPurchase = JointPurchases(name,desc,count,false)

            //Добавляем новую
            jPurchasesViewModel.editDataOnFB(editPurchase)
            Toast.makeText(requireContext(), "Данные изменены", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }else{
            Toast.makeText(requireContext(), "Поле Имя не должно быть пустым", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fbEditData (){
       binding.dialogEditAddname.setText(args.currentPurchases.name)
       binding.dialogEditAdddesc.setText(args.currentPurchases.desc)
       binding.dialogEditAddcount.setText(args.currentPurchases.count.toString())

    }



}