package de.mtgCompanion.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import de.mtgCompanion.shared.model.Player

private const val PLAYER_ARG = "player"

/**
 * The fragment to display a Player.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment : Fragment(R.layout.fragment_player) {
    private var playerJson: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get arguments
        arguments?.let {
            playerJson = it.getString(PLAYER_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val player: Player = Gson().fromJson(playerJson, Player::class.java)

        val view = inflater.inflate(R.layout.fragment_player, container, false)
        view.findViewById<TextView>(R.id.lifeCounter).text =
            player.lifeCounter.amount.toString()

        view.findViewById<Button>(R.id.subbtn).setOnClickListener {
            view.findViewById<TextView>(R.id.lifeCounter).text =
                player.alterPlayersLifeTotalBy(-1).toString()
        }
        view.findViewById<Button>(R.id.addbtn).setOnClickListener {
            view.findViewById<TextView>(R.id.lifeCounter).text =
                player.alterPlayersLifeTotalBy(1).toString()
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
        fun newInstance(player: Player) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(PLAYER_ARG, Gson().toJson(player))
                }
            }
    }
}