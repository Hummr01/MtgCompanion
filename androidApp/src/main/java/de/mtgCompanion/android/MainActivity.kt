package de.mtgCompanion.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), MenuFragment.ButtonClicked {

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

    override fun updateLifeCounterForEachPlayer(text: Int) {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is PlayerFragment) {
                fragment.updateLifeCounter(text)
            }
        }
    }
}
