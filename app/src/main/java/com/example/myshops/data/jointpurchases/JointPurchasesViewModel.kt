package com.example.myshops.data.jointpurchases

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JointPurchasesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<ArrayList<JointPurchases>>
    private val repository: JointPurchasesRepository



init{
    val jointPurchases = ApiFireBase()
    repository = JointPurchasesRepository(jointPurchases)
    readAllData = repository.readAllData
}


     fun addDataToDB(purchases: JointPurchases){
    viewModelScope.launch(Dispatchers.IO) {
    repository.addPurchases(purchases)
}
    }
    fun editDataOnFB(purchases: JointPurchases){
        viewModelScope.launch(Dispatchers.IO){
            repository.editPurchases(purchases)
        }
    }

     fun deleteDataOnFB(purchases: JointPurchases) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePurchases(purchases)

        }


    }
}


