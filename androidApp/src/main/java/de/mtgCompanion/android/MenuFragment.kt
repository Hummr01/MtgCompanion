package de.mtgCompanion.android

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.lang.ClassCastException
import android.view.ViewAnimationUtils


/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {

    var eventCallback: ButtonClicked? = null

    interface ButtonClicked {
        //        fun updateLifeCounterForEachPlayer(text: Int)
        fun startNewGame()
        fun setStartLifeAmount(newAmount: Int)
        fun pickRandomPlayer()
        fun setNumberOfPlayers(newPlayerCount: Int)
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
        eventCallback = null // => avoid leaking
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_circular, container, false)

        // find the Buttons
        val menu = view.findViewById<Button>(R.id.toggleMenu)
        val restart = view.findViewById<Button>(R.id.restart)
        val random = view.findViewById<Button>(R.id.randomPlayer)
        val playerNumber = view.findViewById<Button>(R.id.numberOfPlayers)
        val life = view.findViewById<Button>(R.id.startLife)
        val features = view.findViewById<Button>(R.id.features)

        // set Button on click listeners
        menu.setOnClickListener {
            // create the animator for this view (the start radius is zero)

            arrayOf(restart, random, playerNumber, life, features).forEach { item ->
                val animation: Animator
                val radiusOpened = 100f

                if (item.visibility == View.VISIBLE) {
                    // make visible
                    animation = ViewAnimationUtils.createCircularReveal(
                        item,
                        item.width / 2,
                        item.height / 2,
                        radiusOpened,
                        0f
                    )

                    // make the view invisible when the animation is done
                    animation.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            item.visibility = View.INVISIBLE
                        }
                    })
                } else {
                    // make visible
                    animation = ViewAnimationUtils.createCircularReveal(
                        item,
                        item.width / 2,
                        item.height / 2,
                        0f,
                        radiusOpened
                    )
                    item.visibility = View.VISIBLE
                }

                // start the animation
                animation.start()
            }
        }

        // set onClick methods for the buttons
        restart.setOnClickListener {
            eventCallback!!.startNewGame()
        }
        random.setOnClickListener {
            eventCallback!!.pickRandomPlayer()
        }
        playerNumber.setOnClickListener {
            //TODO: add variables
            eventCallback!!.setNumberOfPlayers(2)
        }
        life.setOnClickListener {
            //TODO: add variables
            eventCallback!!.setStartLifeAmount(40)
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