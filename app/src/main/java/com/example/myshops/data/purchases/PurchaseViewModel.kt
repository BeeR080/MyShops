package com.example.myshops.data.purchases

import android.app.Application
import androidx.lifecycle.*
import com.example.myshops.data.Purchases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PurchaseViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Purchases>>
    private val repository: PurchaseRepository

    init{
        val purchasesDao = PurchasesDatabase.getDatabase(application).purchasesDao()
        repository = PurchaseRepository(purchasesDao)
        readAllData = repository.readAllData

    }
        //Добавить покупку
    fun addPurchases(purchase: Purchases){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPurchases(purchase)

        }
    }
        // Изменить покупку
    fun updatePurchase(purchase: Purchases){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePurchase(purchase)
        }
    }
    // Удалить покупку
    fun deletePurchase(purchase: Purchases){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePurchase(purchase)
        }
    }
    // Удалить все
    fun deleteAllPurchases(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllPurchases()
        }
    }

}