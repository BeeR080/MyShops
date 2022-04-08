package com.example.myshops.view.fragments.purchases
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myshops.R
import com.example.myshops.data.purchases.PurchaseViewModel
import com.example.myshops.data.Purchases
import com.example.myshops.databinding.FragmentAddBinding
import kotlinx.android.synthetic.main.fragment_add.*



class addFragment : Fragment() {


    lateinit var binding: FragmentAddBinding
    private lateinit var mPurchasesViewModel: PurchaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        mPurchasesViewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        binding = FragmentAddBinding.inflate(inflater)


        //Кнопка "+"
        binding.plusbtn.setOnClickListener(){
            var countedit = addcount
            var x = addcount.text.toString().toInt()
            var count = x+1
            countedit.setText(count.toString())
        }
        // Кнока "-"
        binding.minusbtn.setOnClickListener(){
            var countedit = addcount
            var x = addcount.text.toString().toInt()
            var count = x - 1
            if (count < 0) {
                countedit.setText("0").toString()
            } else {
                countedit.setText(count.toString())
            }
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = binding.addname.text.toString()
        val desc = binding.adddesc.text.toString()
        val count = binding.addcount.text.toString().toInt()


        if (inputChek(name)) {
            //Создаем БД
            val purchases = Purchases(0, name, desc,count, checkbox = false)
            //Добавляем данные в ДБ
            mPurchasesViewModel.addPurchases(purchases)
            Toast.makeText(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT).show()
            //Возврващаемся назад после добавления( можно будет потом убрать это, если станет неудобно)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Введите имя товара", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputChek(name: String): Boolean {
        return !(TextUtils.isEmpty(name))

    }
    // Верхний бар
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_barmune_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // Верхний бар, нажатие на эелменты
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.apply_ic -> insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }


}


