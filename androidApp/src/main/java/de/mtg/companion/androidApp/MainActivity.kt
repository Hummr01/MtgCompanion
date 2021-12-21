package de.mtg.companion.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = layoutInflater.inflate(R.layout.lifecounter, null)
        val view2 = layoutInflater.inflate(R.layout.lifecounter, null)


        val layout = findViewById<LinearLayout>(R.id.relativeLayout)

        layout.addView(view)
        layout.addView(view2)

    }

}
