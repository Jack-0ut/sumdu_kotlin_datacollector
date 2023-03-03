package com.example.sumdu_kotlin_datacollector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sumdu_kotlin_datacollector.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // when user clicking on the Confirm button
        binding.buttonConfirm.setOnClickListener {
            if(binding.phoneNumberInput.text.toString().isNotBlank()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("phoneNumber", binding.phoneNumberInput.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this,"Enter the phone number!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}