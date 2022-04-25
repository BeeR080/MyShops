package com.example.myshops.view.fragments.jointpurchases
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myshops.Adapters.ListOfJoinPurchasesAdapter
import com.example.myshops.R
import com.example.myshops.data.jointpurchases.JointPurchasesViewModel
import com.example.myshops.databinding.FragmentJointShopsBinding


class JointShopsFragment() : Fragment() {
   lateinit var binding : FragmentJointShopsBinding
   lateinit var jointPurchasesViewModel: JointPurchasesViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJointShopsBinding.inflate(inflater)

        // RecyclerView
        val adapter = ListOfJoinPurchasesAdapter()
        val recyclerView = binding.recylcerJointshops
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        // ViewModel
        jointPurchasesViewModel = ViewModelProvider(this)
            .get(JointPurchasesViewModel::class.java)
        jointPurchasesViewModel.readAllData.observe(viewLifecycleOwner, Observer {purchases ->
            adapter.setData(purchases)
            binding.progressBar.visibility = View.GONE

        })


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
                val item = adapter.purchasesList[viewHolder.adapterPosition]
                jointPurchasesViewModel.deleteDataOnFB(item)

            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        //Нижний бар
        binding.bottomNavMenuJointshops.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.MyShops ->findNavController()
                    .navigate(R.id.action_jointShopsFragment_to_listFragment)

            }
            true
        }

binding.button2.setOnClickListener {
    findNavController().navigate(R.id.action_jointShopsFragment_to_jointShopsDialogFragment)
}
        return binding.root
    }


//Для отображения нижней иконки
    override fun onStart() {
        binding.bottomNavMenuJointshops.selectedItemId = R.id.JointShops
        super.onStart()
    }
}
