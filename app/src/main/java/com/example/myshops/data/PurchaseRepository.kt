package com.example.myshops.data



import androidx.lifecycle.LiveData

class PurchaseRepository(private val purchasesDao: PurchasesDao) {

    val readAllData: LiveData<List<Purchases>> = purchasesDao.readAllData()
    val readCheked: LiveData<List<Purchases>> = purchasesDao.readCheked()

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

