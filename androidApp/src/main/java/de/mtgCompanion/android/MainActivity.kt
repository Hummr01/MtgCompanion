package de.mtgCompanion.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), MenuFragment.TextClicked {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            MyApplication.appService.setNumberOfPlayers(2)

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragmentPlayerOne, PlayerFragment.newInstance(0))
                add(R.id.fragmentPlayerTwo, PlayerFragment.newInstance(1))
                add(R.id.fragmentMenu, MenuFragment.newInstance())
            }
        }
    }

    override fun sendText(text: String?, playerId: Int) {
        val frag: PlayerFragment = supportFragmentManager.findFragmentById(playerId) as PlayerFragment
        frag.updateText(text)
    }
}
