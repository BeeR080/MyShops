package com.example.myshops.view.fragments
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myshops.R
import com.example.myshops.data.JointPurchases
import com.example.myshops.data.JointPurchasesViewModel
import com.example.myshops.data.PurchaseViewModel
import com.example.myshops.data.Purchases
import com.example.myshops.databinding.FragmentJointShopsDialogBinding
import kotlinx.android.synthetic.main.fragment_joint_shops_dialog.*


class JointShopsDialogFragment : Fragment() {

    lateinit var binding: FragmentJointShopsDialogBinding
    lateinit var jointPurchasesViewModel: JointPurchasesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentJointShopsDialogBinding.inflate(inflater)
jointPurchasesViewModel = ViewModelProvider(this).get(JointPurchasesViewModel::class.java)

    //Кнопка "+"
    binding.dialogPlusbtn.setOnClickListener() {
        var countedit = dialog_addcount
        var x = dialog_addcount.text.toString().toInt()
        var count = x + 1
        countedit.setText(count.toString())
    }
    // Кнока "-"
    binding.dialogMinusbtn.setOnClickListener() {
        var countedit = dialog_addcount
        var x = dialog_addcount.text.toString().toInt()
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
        inflater.inflate(R.menu.action_barmune_add, menu)


        super.onCreateOptionsMenu(menu, inflater)
    }
    // Верхний бар, нажатие на эелменты
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.apply_ic -> insertDataToFB()
        }
        return super.onOptionsItemSelected(item)
    }

    fun insertDataToFB(){
       val count =  binding.dialogAddcount.text.toString().toInt()
        val desc = binding.dialogAdddesc.text.toString()
        val name = binding.dialogAddname.text.toString()

        val purchases = JointPurchases(name,desc,count,false)
        //Добавляем данные в ДБ
        jointPurchasesViewModel.addDataToDB(purchases)
        findNavController().navigate(R.id.action_jointShopsDialogFragment_to_jointShopsFragment)
    }


    }




