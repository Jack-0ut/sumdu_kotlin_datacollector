package com.example.sumdu_kotlin_datacollector

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sumdu_kotlin_datacollector.databinding.ActivityWebBinding


class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // when user clicking on the Confirm button
        binding.buttonConfirm.setOnClickListener {
            if(binding.webUrlInput.text.toString().isNotBlank()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("url", binding.webUrlInput.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this,"Enter the web url!!!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}