package edu.uw.ischool.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    // TODO: Make a kick-ass application
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the App state to "bar"
        var myAppObject = applicationContext as MyApplication
        myAppObject.state = "bar"

        setContentView(R.layout.activity_main)
    }
}
