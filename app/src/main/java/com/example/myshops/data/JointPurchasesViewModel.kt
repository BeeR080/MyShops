package com.example.myshops.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JointPurchasesViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<ArrayList<JointPurchases>>
    private val repository: JointPurchasesRepository
    val fireBase  = ApiFireBase()



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


}
