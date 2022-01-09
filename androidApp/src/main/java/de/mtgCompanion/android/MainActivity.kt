package de.mtgCompanion.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.gson.Gson
import de.mtgCompanion.shared.model.AppService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            MyApplication.appService.setNumberOfPlayers(2)

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<PlayerFragment>(R.id.fragmentPlayerOne, args = bundleOf("player" to Gson().toJson(MyApplication.appService.playerList[0])))
                add<PlayerFragment>(R.id.fragmentPLayerTwo, args = bundleOf("player" to Gson().toJson(MyApplication.appService.playerList[1])))
                add<menuFragment>(R.id.fragmentMenu)
            }
        }
    }
}
