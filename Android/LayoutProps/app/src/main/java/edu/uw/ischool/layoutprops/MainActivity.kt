package edu.uw.ischool.layoutprops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            view -> {
                (findViewById<TextView>(R.id.helloText) as TextView).text = "Changed at runtime"
                val params = view.layoutParams as LinearLayout.LayoutParams
                params.leftMargin += 5
            }
        }
    }
}
