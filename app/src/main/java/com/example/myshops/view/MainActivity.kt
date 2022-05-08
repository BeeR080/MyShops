package com.example.myshops.view


import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myshops.R
import com.example.myshops.Services.JointPurchaseService
import com.example.myshops.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startService(JointPurchaseService.newIntent(this))


        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.jointShopsFragment, R.id.listFragment)
            .build()


        //Добавление стрелки Назад на баре
        setupActionBarWithNavController(findNavController(R.id.fragment),appBarConfiguration)



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }




}