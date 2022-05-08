package com.example.myshops.view.fragments.jointpurchases
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myshops.R
import com.example.myshops.Services.JointPurchaseService
import com.example.myshops.data.jointpurchases.JointPurchases
import com.example.myshops.data.jointpurchases.JointPurchasesViewModel
import com.example.myshops.databinding.FragmentJointShopsDialogBinding

import kotlinx.android.synthetic.main.fragment_joint_shops_dialog.*


class JointShopsAddFragment : Fragment() {

    lateinit var binding: FragmentJointShopsDialogBinding
    lateinit var jointPurchasesViewModel: JointPurchasesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentJointShopsDialogBinding.inflate(inflater)
jointPurchasesViewModel = ViewModelProvider(this).get(JointPurchasesViewModel::class.java)

    //Кнопка "+"
    binding.dialogPlusbtn.setOnClickListener() {
        var countedit = dialog_addcount
        var x = dialog_addcount.text.toString().toInt()
        var count = x + 1
        countedit.setText(count.toString())
    }
    // Кнока "-"
    binding.dialogMinusbtn.setOnClickListener() {
        var countedit = dialog_addcount
        var x = dialog_addcount.text.toString().toInt()
        var count = x - 1
        if (count < 0) {
            countedit.setText("0").toString()
        } else {
            countedit.setText(count.toString())
        }
    }


        return binding.root
    }


    // Верхний бар
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_barmune_add, menu)


        super.onCreateOptionsMenu(menu, inflater)
    }
    // Верхний бар, нажатие на эелменты
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.apply_ic -> insertDataToFB()
        }
        return super.onOptionsItemSelected(item)
    }

    fun insertDataToFB(){
       val count =  binding.dialogAddcount.text.toString().toInt()
        val desc = binding.dialogAdddesc.text.toString()
        val name = binding.dialogAddname.text.toString()

        val purchases = JointPurchases(name,desc,count,false)
        //Добавляем данные в FB
        jointPurchasesViewModel.addDataToDB(purchases)
        findNavController().navigate(R.id.action_jointShopsDialogFragment_to_jointShopsFragment)
        showNotification(purchases.name)
    }

    fun showNotification(purcshase:String){
        val notificationManager = activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)


        val notification = Notification.Builder(requireContext(), CHANNEL_ID)
            .setContentTitle("Покупка")
            .setContentText("Доавлено: $purcshase")
            .setSmallIcon(R.drawable.purchasessicons1_foreground)
            .build()


        notificationManager.notify(1, notification)
    }



    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"

    }

    }




