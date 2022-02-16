package de.mtgCompanion.android

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment


/**
 * The main menu [Fragment]
 *
 * Pick start player, start life, number of players,
 *
 * Shows or hides the menu.
 * Backdrop click is possible to hide the menu.
 */
class MainMenuFragment : Fragment() {
    private var eventCallback: ButtonClicked? = null

    interface ButtonClicked {
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
        val menuButton = view.findViewById<ImageView>(R.id.menu)
        val restartButton = view.findViewById<ImageView>(R.id.restart)
        val randomPlayerButton = view.findViewById<ImageView>(R.id.randomPlayer)
        val numberOfPlayersButton = view.findViewById<ImageView>(R.id.numberOfPlayers)
        val startLifeButton = view.findViewById<ImageView>(R.id.startLife)
        val cardSearchButton = view.findViewById<ImageView>(R.id.search)

        // set onClick for View to hide menu on backdrop click
        view.setOnClickListener {
            // only clickable while menu is visible
            toggleMenuVisibility()
        }

        // set onClick methods for the buttons
        // Menu
        menuButton.setOnClickListener {
            toggleMenuVisibility()
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
        cardSearchButton.setOnClickListener {
            //TODO: Implement it!
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // hide menu
        requireView().findViewById<Group>(R.id.mainMenu).visibility = View.INVISIBLE

        // transparent background when menu is open
        view.setBackgroundColor(Color.WHITE)
        view.background.alpha = 0

        // The View should only be clickable while menu is visible
        requireView().isClickable = false
        requireView().isFocusable = false
    }

    private fun toggleMenuVisibility() {
        val mainMenu = requireView().findViewById<Group>(R.id.mainMenu)

        animateCircleMenu(mainMenu.referencedIds)

        // toggle the clickable state. View should only be clickable while menu is visible.
        if (mainMenu.visibility == View.VISIBLE) {
            requireView().background.alpha = 0
            mainMenu.visibility = View.INVISIBLE
            requireView().isClickable = false
            requireView().isFocusable = false
        } else {
            // alpha value to darken the background
            requireView().background.alpha = 140
            mainMenu.visibility = View.VISIBLE
            requireView().isClickable = true
            requireView().isFocusable = true
        }
    }

    // handles the animation of the circle menu
    private fun animateCircleMenu(viewIdArray: IntArray) {
        val duration = 200L
        val radius = 300

        // from hidden to visible
        var startRadius = 0
        var endRadius = radius
        var startScale = 0f
        var endScale = 1f

        // from visible to hidden
        if (requireView().findViewById<Group>(R.id.mainMenu).visibility == View.VISIBLE) {
            startRadius = radius
            endRadius = 0
            startScale = 1f
            endScale = 0f
        }

        viewIdArray.forEach {
            val view = requireView().findViewById<View>(it)

            // define scale animation
            val scaleAnimation = ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            scaleAnimation.duration = duration
            scaleAnimation.interpolator = LinearInterpolator()

            // define radius animation
            val radiusAnim = ValueAnimator.ofInt(startRadius, endRadius)
            radiusAnim.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.circleRadius = value
                view.layoutParams = layoutParams
            }

            // set duration an interpolation of animation
            radiusAnim.duration = duration
            radiusAnim.interpolator = LinearInterpolator()

            // start animation
            radiusAnim.start()
            view.startAnimation(scaleAnimation)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment menuFragment.
         */
        @JvmStatic
        fun newInstance() = MainMenuFragment()
    }
}