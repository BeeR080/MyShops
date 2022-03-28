package com.example.myshops.view.fragments
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myshops.Adapters.ListOfJoinPurchasesAdapter
import com.example.myshops.R
import com.example.myshops.data.*
import com.example.myshops.databinding.FragmentJointShopsBinding
import com.google.firebase.ktx.Firebase


class JointShopsFragment() : Fragment() {
   lateinit var binding : FragmentJointShopsBinding
   lateinit var jointPurchasesViewModel: JointPurchasesViewModel
    val adapter = ListOfJoinPurchasesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentJointShopsBinding.inflate(inflater)


        // RecyclerView
        val recyclerView = binding.recylcerJointshops
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        //Нижний бар
        binding.bottomNavMenuJointshops.selectedItemId = R.id.JointShops
        binding.bottomNavMenuJointshops.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.MyShops ->findNavController().navigate(R.id.action_jointShopsFragment_to_listFragment)
            }
            true
        }
        //ViewModel
        jointPurchasesViewModel = ViewModelProvider(this).get(JointPurchasesViewModel::class.java)
        jointPurchasesViewModel.readAllData.observe(viewLifecycleOwner, Observer {purchases ->
            adapter.setData(purchases)

        })

binding.button2.setOnClickListener {
    findNavController().navigate(R.id.action_jointShopsFragment_to_jointShopsDialogFragment)
   // jointPurchasesViewModel.addDataToDB()
}

        return binding.root

    }











}
