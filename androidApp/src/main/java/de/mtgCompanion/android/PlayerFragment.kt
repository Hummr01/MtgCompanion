package de.mtgCompanion.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import de.mtgCompanion.shared.model.Player

private const val PLAYER_ID_ARG = "id"

/**
 * The fragment to display a Player.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment : Fragment() {
    private var playerId: Int = -1
    val playerListener: MutableLiveData<Int> = MutableLiveData()

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
        playerListener.value = MyApplication.appService.getPlayerByIndex(playerId).lifeCounter.amount
        val view = inflater.inflate(R.layout.fragment_player, container, false)

        playerListener.observe(this, {
            view.findViewById<TextView>(R.id.lifeCounter).text =
                playerListener.value.toString()
        })

        view.findViewById<Button>(R.id.subbtn).setOnClickListener {
                MyApplication.appService.getPlayerByIndex(playerId).alterPlayersLifeTotalBy(-1).toString()
        }

        view.findViewById<Button>(R.id.addbtn).setOnClickListener {
                MyApplication.appService.getPlayerByIndex(playerId).alterPlayersLifeTotalBy(1).toString()
        }

        return view
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