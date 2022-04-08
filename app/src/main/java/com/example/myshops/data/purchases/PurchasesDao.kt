package com.example.myshops.data.purchases

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myshops.data.Purchases

@Dao
interface PurchasesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPurchases(purchases: Purchases)

    @Update
    suspend fun updatePurchase(purchases: Purchases)

    @Delete
    suspend fun deletePurchase(purchases: Purchases)

    @Query("DELETE FROM my_purchases")
    suspend fun deleteAllPurchases()

    @Query("SELECT * FROM my_purchases ORDER BY id ASC")
    fun readAllData(): LiveData<List<Purchases>>
}