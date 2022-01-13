package de.mtgCompanion.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Controls the UI of all players
 */
class PlayerViewModel : ViewModel() {

    val life = MutableLiveData<Int>()

    fun setLife(life: Int) {
        MyApplication.appService.setPlayerStartLifeAmountTo(life)
        this.life.value = life
    }
}

class MainActivity : AppCompatActivity() {

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
}
