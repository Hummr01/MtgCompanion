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

    private fun updateLifeCounterForEachPlayer(newAmount: Int) {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is PlayerFragment) {
                fragment.updateLifeCounter(newAmount)
            }
        }
    }

    override fun startNewGame() {
        MyApplication.appService.startNewGame()
        updateLifeCounterForEachPlayer(MyApplication.appService.startLife)
    }

    override fun setStartLifeAmount(newAmount: Int) {
        MyApplication.appService.setPlayerStartLifeAmountTo(newAmount)
        updateLifeCounterForEachPlayer(newAmount)
    }

    override fun pickRandomPlayer() {
        val player = MyApplication.appService.choosePlayerAtRandom()
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is PlayerFragment) {
                if (MyApplication.appService.getPlayerByIndex(fragment.playerId) == player) {
                    fragment.showPicked()
                }
            }
        }
    }

    override fun setNumberOfPlayers(newPlayerCount: Int) {
        MyApplication.appService.setNumberOfPlayers(newPlayerCount)
        updateLifeCounterForEachPlayer(MyApplication.appService.startLife)
    }
}
