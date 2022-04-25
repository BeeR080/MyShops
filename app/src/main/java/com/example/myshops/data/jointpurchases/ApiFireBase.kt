package com.example.myshops.data.jointpurchases

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
          private var _purchasesList: MutableLiveData<ArrayList<JointPurchases>> = MutableLiveData()



      fun setDataToFB(purchases: JointPurchases){
        dbRef.child(purchases.name).setValue(purchases)
    }

     fun getDataFromFB(): LiveData<ArrayList<JointPurchases>> {
         dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot: DataSnapshot in snapshot.children){
                    val purchase = datasnapshot.getValue(JointPurchases::class.java)
                    purchasesList.add(purchase!!)
                    _purchasesList.value = purchasesList

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
                     purchasesList.clear()

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
                     purchasesList.clear()


                 }
             }

             override fun onCancelled(error: DatabaseError) {

             }


         })
     }

}


