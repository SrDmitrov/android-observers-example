package es.rudo.connectivitymanagerexample.livedata

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.rudo.connectivitymanagerexample.R

class LivedataActivity : AppCompatActivity() {

    private lateinit var networkManager: LiveDataNetworkManager
    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        viewModel = LiveDataViewModel(this)

        watchConnection()

    }

    private fun watchConnection() {
        viewModel.hasInternet.observe(this) {
            if (it) {
                findViewById<TextView>(R.id.myText).text = "Connected"
            } else {
                findViewById<TextView>(R.id.myText).text = "No internet"
            }
        }
    }
}