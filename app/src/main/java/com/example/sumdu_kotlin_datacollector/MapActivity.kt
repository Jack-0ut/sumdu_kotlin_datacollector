package com.example.sumdu_kotlin_datacollector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sumdu_kotlin_datacollector.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // when user clicking on the Confirm button
        binding.buttonConfirm.setOnClickListener {
            if(binding.xInput.text.toString().isNotBlank() && binding.yInput.text.toString().isNotBlank()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("X", binding.xInput.text.toString())
                intent.putExtra("Y", binding.yInput.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this,"Enter the latitude and the longitude!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}