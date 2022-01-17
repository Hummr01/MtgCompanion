package de.mtgCompanion.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.lang.ClassCastException


/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {

    var eventCallback: ButtonClicked? = null

    interface ButtonClicked {
        fun updateLifeCounterForEachPlayer(text: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        eventCallback = try {
            context as ButtonClicked
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement TextClicked"
            )
        }
    }

    override fun onDetach() {
        eventCallback = null // => avoid leaking, thanks @Deepscorn
        super.onDetach()
    }

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
            eventCallback!!.updateLifeCounterForEachPlayer(MyApplication.appService.startLife)
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
            MyApplication.appService.setPlayerStartLifeAmountTo(40)
            eventCallback!!.updateLifeCounterForEachPlayer(40)
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