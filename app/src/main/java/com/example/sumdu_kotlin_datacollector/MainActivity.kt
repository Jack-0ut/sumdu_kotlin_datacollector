package com.example.sumdu_kotlin_datacollector

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sumdu_kotlin_datacollector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val groups = mutableListOf<Group>()
    private val adapter = ExpandableListAdapter(groups)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the group names
        groups.add(Group("Phone Number"))
        groups.add(Group("Web URL"))
        groups.add(Group("Map Location"))

        binding.activitiesListView.setAdapter(adapter)

        binding.mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivityForResult(intent, MAP_ACTIVITY_REQUEST_CODE)
        }

        binding.phoneButton.setOnClickListener {
            val intent = Intent(this, PhoneActivity::class.java)
            startActivityForResult(intent, PHONE_ACTIVITY_REQUEST_CODE)
        }

        binding.webButton.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivityForResult(intent, WEB_ACTIVITY_REQUEST_CODE)
        }

        val adapter = ExpandableListAdapter(groups)
        binding.activitiesListView.setAdapter(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // if previous activity was "PhoneActivity"
            PHONE_ACTIVITY_REQUEST_CODE -> {
                val phoneNumber = data?.getStringExtra("phoneNumber")
                if (phoneNumber != null) {
                    groups.find { it.name == "Phone Number" }?.addChild(phoneNumber)
                    adapter.notifyDataSetChanged()
                }
            }
            // if previous activity was "WebActivity"
            WEB_ACTIVITY_REQUEST_CODE -> {
                val url = data?.getStringExtra("url")
                if (url != null) {
                    groups.find { it.name == "Web URL" }?.addChild(url)
                    adapter.notifyDataSetChanged()
                }
            }
            MAP_ACTIVITY_REQUEST_CODE -> {
                val latitude = data?.getStringExtra("X")?.toDoubleOrNull()
                val longitude = data?.getStringExtra("Y")?.toDoubleOrNull()
                // if user entered the coordinates, concatenate it using ,
                if (latitude != null && longitude != null) {
                    groups.find { it.name == "Map Location" }
                        ?.addChild("$latitude,$longitude")
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }


    companion object {
        private const val MAP_ACTIVITY_REQUEST_CODE = 1
        private const val PHONE_ACTIVITY_REQUEST_CODE = 2
        private const val WEB_ACTIVITY_REQUEST_CODE = 3
    }
}
