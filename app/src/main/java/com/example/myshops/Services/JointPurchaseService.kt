package com.example.myshops.Services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.util.Log

class JointPurchaseService: Service() {

    override fun onCreate() {
        super.onCreate()
        log("create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)


        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        log("destroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }



    companion object{
fun log(message: String){
    Log.d("service", "$message")
}
        fun newIntent(context: Context): Intent{
            return Intent(context, JointPurchaseService::class.java)
        }
    }
}