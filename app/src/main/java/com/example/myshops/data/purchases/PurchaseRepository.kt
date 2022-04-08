package com.example.myshops.data.purchases



import androidx.lifecycle.LiveData
import com.example.myshops.data.Purchases

class PurchaseRepository(private val purchasesDao: PurchasesDao) {

    val readAllData: LiveData<List<Purchases>> = purchasesDao.readAllData()

   suspend fun addPurchases(purchases: Purchases){
        purchasesDao.addPurchases(purchases)
    }

    suspend fun updatePurchase(purchases: Purchases){
        purchasesDao.updatePurchase(purchases)
    }

    suspend fun  deletePurchase(purchases: Purchases){
        purchasesDao.deletePurchase(purchases)
    }

    suspend fun deleteAllPurchases(){
         purchasesDao.deleteAllPurchases()

    }


}

