package com.newardassociates.controls

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Button


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change the TextView to read the current time 6:25
        val button = findViewById<Button>(R.id.btnHello)
        button.setOnClickListener {
            Log.v(TAG, "User clicked the button marked " + button.text)
        }
    }
}
