package com.example.myshops.data.jointpurchases

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
     val dbRef = dataBase.getReference("purchases")
     var purchasesList = ArrayList<JointPurchases>()
      var _purchasesList: MutableLiveData<List<JointPurchases>> = MutableLiveData()


     fun setDataToFB(purchases: JointPurchases){
        dbRef.child(purchases.name).setValue(purchases)
    }

     fun getDataFromFB(): LiveData<List<JointPurchases>> {

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

     fun deleteDataFromFB(purchases: JointPurchases){
         dbRef.addListenerForSingleValueEvent(object  : ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 for(datasnapshot: DataSnapshot in snapshot.children){
                     dbRef.child(purchases.name).removeValue()

                 }
             }

             override fun onCancelled(error: DatabaseError) {
             }
         })
     }

     fun editDataFromFB(purchases: JointPurchases){
         dbRef.addListenerForSingleValueEvent(object : ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 for(datasnapshot: DataSnapshot in snapshot.children){
                     dbRef.child(purchases.name).setValue(purchases)


                 }
             }

             override fun onCancelled(error: DatabaseError) {

             }


         })
     }
}

