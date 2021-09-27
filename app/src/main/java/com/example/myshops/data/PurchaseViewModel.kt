package com.example.myshops.data

import android.app.Application
import android.widget.CheckBox
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PurchaseViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Purchases>>
    val readCheked: LiveData<List<Purchases>>
    private val repository: PurchaseRepository

    init{
        val purchasesDao = PurchasesDatabase.getDatabase(application).purchasesDao()
        repository = PurchaseRepository(purchasesDao)
        readAllData = repository.readAllData
        readCheked = repository.readCheked
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