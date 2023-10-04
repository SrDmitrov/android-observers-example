package es.rudo.connectivitymanagerexample.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import es.rudo.connectivitymanagerexample.R

class LivedataActivity : AppCompatActivity() {

    private lateinit var networkManager: LiveDataNetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        networkManager = LiveDataNetworkManager(this)
        networkManager.observe(this){
            if (it) {
                findViewById<TextView>(R.id.myText).text = "Connected"
            }else {
                findViewById<TextView>(R.id.myText).text = "No internet"
            }
        }
    }
}