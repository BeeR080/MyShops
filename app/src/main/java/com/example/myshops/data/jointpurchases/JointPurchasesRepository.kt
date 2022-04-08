package com.example.myshops.data.jointpurchases
import androidx.lifecycle.LiveData


class JointPurchasesRepository( private val fireBase: ApiFireBase) {


    val readAllData: LiveData<List<JointPurchases>> = fireBase.getDataFromFB()


  suspend  fun addPurchases(purchases: JointPurchases){
     fireBase.setDataToFB(purchases)
    }

   suspend fun editPurchases(purchases: JointPurchases){
       fireBase.editDataFromFB(purchases)
    }

   suspend fun deletePurchases(purchases: JointPurchases){
       fireBase.deleteDataFromFB(purchases)
    }




}