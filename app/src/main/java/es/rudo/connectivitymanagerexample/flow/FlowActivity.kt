package es.rudo.connectivitymanagerexample.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import es.rudo.connectivitymanagerexample.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FlowActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: FlowConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        // Initialize connectivity observer
        connectivityObserver = NetworkFlowConectivityObserver(applicationContext)

        // Get actual connectivity status
        val status = connectivityObserver
            .observe()

        // Sets up the listener to be triggered **on each change** of the connectivity status
        status.onEach {
            findViewById<TextView>(R.id.myText).text = "network status -> ${it.name}"
        }
            .launchIn(lifecycleScope)
    }
}