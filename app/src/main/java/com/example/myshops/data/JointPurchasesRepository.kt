package com.example.myshops.data
import androidx.lifecycle.LiveData


class JointPurchasesRepository( private val fireBase: ApiFireBase) {


    val readAllData: LiveData<ArrayList<JointPurchases>> = fireBase.getDataFromFB()

/*    fun getPurchases() {
    fireBase.getDataFromFB()
    }*/

    fun addPurchases(purchases: JointPurchases){
     fireBase.setDataToFB(purchases)
    }




}