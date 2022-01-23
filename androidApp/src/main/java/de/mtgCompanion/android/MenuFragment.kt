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
import kotlin.math.hypot

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
        val menuButton = view.findViewById<Button>(R.id.toggleMenu)
        val restartButton = view.findViewById<Button>(R.id.restart)
        val randomPlayerButton = view.findViewById<Button>(R.id.randomPlayer)
        val numberOfPlayersButton = view.findViewById<Button>(R.id.numberOfPlayers)
        val startLifeButton = view.findViewById<Button>(R.id.startLife)
        val cardSearchButton = view.findViewById<Button>(R.id.features)

        // set onClick methods for the buttons
        // Menu
        menuButton.setOnClickListener {
            arrayOf(
                restartButton,
                randomPlayerButton,
                numberOfPlayersButton,
                startLifeButton,
                cardSearchButton
            ).forEach { item ->
                animateVisibility(item)
            }
        }
        // restart round
        restartButton.setOnClickListener {
            eventCallback!!.startNewGame()
        }
        // select random player
        randomPlayerButton.setOnClickListener {
            eventCallback!!.pickRandomPlayer()
        }
        // set number of players
        numberOfPlayersButton.setOnClickListener {
            //TODO: add variables
            eventCallback!!.setNumberOfPlayers(2)
        }
        // set start life amount
        startLifeButton.setOnClickListener {
            //TODO: add variables
            eventCallback!!.setStartLifeAmount(40)
        }

        return view
    }

    private fun animateVisibility(item: View) {
        val animation: Animator
        val centerX = item.width / 2
        val centerY = item.height / 2

        // get the initial radius for the clipping circle
        val radiusOpened = hypot(centerX.toDouble(), centerY.toDouble()).toFloat()

        if (item.visibility == View.VISIBLE) {
            // make visible
            animation = ViewAnimationUtils.createCircularReveal(
                item,
                centerX,
                centerY,
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
                centerX,
                centerY,
                0f,
                radiusOpened
            )

            item.visibility = View.VISIBLE
        }

        // start the animation
        animation.start()
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