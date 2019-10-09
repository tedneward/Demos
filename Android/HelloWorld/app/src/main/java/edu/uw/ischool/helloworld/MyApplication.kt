package edu.uw.ischool.helloworld

import android.app.Application
import android.util.Log

interface StateRepository {
    var state : String
    fun getState()
    fun putState(newState : String)
}

class MockStateRepository : StateRepository {

    override var state : String = ""

    override fun getState() {
        state = "Boo"// results from downloading from server
    }
    override fun putState(newState : String) {
        state = newState
    }
}

class HttpStateRepository : StateRepository {
    override var state : String = ""

    override fun getState() {
        state = "Boo"// results from downloading from server
    }
    override fun putState(newState : String) {
        state = newState
    }
}



class MyApplication : Application() {

    private val theRepository : StateRepository =
        if (productionSetting) HttpStateRepository() else MockStateRepository()

    override fun onCreate() {
        super.onCreate()

        Log.v("MyApplication", "We got created")
    }

    var state : String {
        get { return theRepository.state }
    }
}
