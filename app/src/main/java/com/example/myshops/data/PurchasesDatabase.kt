package com.example.myshops.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Purchases::class], version = 1, exportSchema = false )
abstract class PurchasesDatabase: RoomDatabase() {

    abstract fun  purchasesDao(): PurchasesDao

    companion object{
        @Volatile
        private var INSTANCE: PurchasesDatabase? = null

        fun getDatabase(context: Context): PurchasesDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PurchasesDatabase::class.java,
                        "purchases_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}