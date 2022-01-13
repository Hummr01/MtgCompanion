package de.mtgCompanion.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {

    private val model: PlayerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // set Button on click listeners
        view.findViewById<Button>(R.id.restart).setOnClickListener {
            //TODO: must trigger UI update
            MyApplication.appService.startNewGame()
        }
        view.findViewById<Button>(R.id.randomPlayer).setOnClickListener {
            MyApplication.appService.choosePlayerAtRandom()
        }
        view.findViewById<Button>(R.id.toggleVisibility).setOnClickListener {
            view.visibility = View.GONE
        }
        view.findViewById<Button>(R.id.numberOfPlayers).setOnClickListener {
            //TODO: add variables
            MyApplication.appService.setNumberOfPlayers(2)
        }
        view.findViewById<Button>(R.id.startLife).setOnClickListener {
            //TODO: add variables
            model.setLife(40)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment menuFragment.
         */
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}