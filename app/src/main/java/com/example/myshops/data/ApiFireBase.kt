package com.example.myshops.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

 class ApiFireBase {
    val dataBase = Firebase.database
     var purchasesList = ArrayList<JointPurchases>()
      var _purchasesList: MutableLiveData<ArrayList<JointPurchases>> = MutableLiveData()


     fun setDataToFB(purchases: JointPurchases){
        val dbRef = dataBase.getReference("purchases")
        dbRef.child(purchases.name).setValue(purchases)
        Log.d("Firebase","Data send")
    }

    fun getDataFromFB(): LiveData<ArrayList<JointPurchases>> {
        val dbRef = dataBase.getReference("purchases")
         dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot: DataSnapshot in snapshot.children){
                    val purchase = datasnapshot.getValue(JointPurchases::class.java)
                    purchasesList.add(purchase!!)
                    _purchasesList.value = purchasesList


                    Log.d("Firebase", "See data on FB $purchasesList")
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })

        return _purchasesList


    }

}

