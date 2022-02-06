package com.example.myshops.view.fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshops.Adapters.ListOfJoinPurchasesAdapter
import com.example.myshops.R
import com.example.myshops.data.JointPurchases
import com.example.myshops.databinding.FragmentJointShopsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class JointShopsFragment : Fragment() {
   lateinit var binding : FragmentJointShopsBinding
    val adapter = ListOfJoinPurchasesAdapter()
   val database = Firebase.database
    val purchasesList = ArrayList<JointPurchases>()

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
        getDataFromFB()



        //Нижний бар
        binding.bottomNavMenuJointshops.selectedItemId = R.id.JointShops
        binding.bottomNavMenuJointshops.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.MyShops ->findNavController().navigate(R.id.action_jointShopsFragment_to_listFragment)
            }
            true
        }

binding.button2.setOnClickListener {
    setValuetoDB()
}
        return binding.root


    }
    fun setValuetoDB(){
        val dbRef = database.getReference("purchases")
        val purchaseId = dbRef.push().key
        val purchases = JointPurchases(purchaseId!!,"Tvorog","9%, Domik v derevne",3)
        dbRef.child(purchases.name).setValue(purchases)

        Log.d("Firebase","Data send")


    }
    fun getDataFromFB(){
        val dbRef = database.getReference("purchases")
        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                for(datasnapshot: DataSnapshot in snapshot.children){

                val purchase = datasnapshot.getValue(JointPurchases::class.java)
                purchasesList.add(purchase!!)
                adapter.setData(purchasesList)

                Log.d("Firebase", " dobavleno: ${purchasesList.get(0)}")
            }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

}
