package de.mtgCompanion.android

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

private const val PLAYER_ID_ARG = "id"

/**
 * The fragment to display a Player.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment : Fragment() {
    var playerId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get arguments
        arguments?.let {
            playerId = it.getInt(PLAYER_ID_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        val player = MyApplication.appService.getPlayerByIndex(playerId)
        val lifeCounter = view.findViewById<TextView>(R.id.lifeCounter)

        // initially set the life of the player
        lifeCounter.text = player.lifeCounter.amount.toString()

        // Add and subtract methods
        view.findViewById<Button>(R.id.subbtn).setOnClickListener {
            lifeCounter.text =
                MyApplication.appService.getPlayerByIndex(playerId).alterPlayersLifeTotalBy(-1)
                    .toString()
        }
        view.findViewById<Button>(R.id.addbtn).setOnClickListener {
            lifeCounter.text =
                MyApplication.appService.getPlayerByIndex(playerId).alterPlayersLifeTotalBy(1)
                    .toString()
        }

        return view
    }

    fun updateLifeCounter(newAmount: Int) {
        view!!.findViewById<TextView>(R.id.lifeCounter).text = newAmount.toString()
    }

    fun showPicked() {
        view!!.setBackgroundColor(Color.parseColor("#F44336"))
        view!!.postDelayed({
            view!!.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }, 1000)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param player the player set for this fragment.
         * @return A new instance of fragment Player.
         */
        @JvmStatic
        fun newInstance(playerId: Int) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putInt(PLAYER_ID_ARG, playerId)
                }
            }
    }
}