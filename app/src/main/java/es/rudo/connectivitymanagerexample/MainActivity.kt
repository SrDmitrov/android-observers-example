package es.rudo.connectivitymanagerexample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import es.rudo.connectivitymanagerexample.flow.FlowActivity
import es.rudo.connectivitymanagerexample.livedata.LivedataActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_flow).setOnClickListener {
            startActivity(
                    Intent(
                            this,
                            FlowActivity::class.java
                    )
            )
        }
        findViewById<Button>(R.id.btn_livedata).setOnClickListener {
            startActivity(
                    Intent(
                            this,
                            LivedataActivity::class.java
                    )
            )
        }

    }
}