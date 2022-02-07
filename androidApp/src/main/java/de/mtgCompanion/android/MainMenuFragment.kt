package de.mtgCompanion.android

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
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
    private var visible: Boolean = false

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
        val menuButton = view.findViewById<Button>(R.id.toggleMenu)
        val restartButton = view.findViewById<Button>(R.id.restart)
        val randomPlayerButton = view.findViewById<Button>(R.id.randomPlayer)
        val numberOfPlayersButton = view.findViewById<Button>(R.id.numberOfPlayers)
        val startLifeButton = view.findViewById<Button>(R.id.startLife)
        val cardSearchButton = view.findViewById<Button>(R.id.features)

        // set onClick for View to enable backdrop click
        view.setOnClickListener {
            // only clickable while menu is visible
            // hides the menu on backdrop click
            hideMenu()
        }

//        val anim = ValueAnimator.ofInt(150, 300)
//        anim.addUpdateListener { valueAnimator ->
//            val `val` = valueAnimator.animatedValue as Int
//            val layoutParams = menuButton.layoutParams as ConstraintLayout.LayoutParams
//            layoutParams.circleRadius = `val`
//            menuButton.layoutParams = layoutParams
//        }
////            anim.duration = duration
//        anim.interpolator = LinearInterpolator()
//        anim.repeatMode = ValueAnimator.REVERSE
//        anim.repeatCount = ValueAnimator.INFINITE

        // set onClick methods for the buttons
        // Menu
        menuButton.setOnClickListener {
//            anim.start()
            if (visible) {
                hideMenu()
            } else {
                showMenu()
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
        cardSearchButton.setOnClickListener {
            //TODO: Implement it!
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set initially to false. The View should only be clickable while menu is visible
        changeMenuVisibilityTo(false)
    }

    private fun showMenu() {
        if (visible) {
            // already visible
            return
        }
        arrayListOf<View>(
            requireView().findViewById(R.id.restart),
            requireView().findViewById(R.id.randomPlayer),
            requireView().findViewById(R.id.numberOfPlayers),
            requireView().findViewById(R.id.startLife),
            requireView().findViewById(R.id.features)
        ).forEach { item ->
            item.visibility = View.VISIBLE
            animateCircleMenu(item)
        }

        // toggle the clickable state. View should only be clickable while menu is visible.
        changeMenuVisibilityTo(true)
    }

    private fun hideMenu() {
        if (!visible) {
            // already hidden
            return
        }
        arrayListOf<View>(
            requireView().findViewById(R.id.restart),
            requireView().findViewById(R.id.randomPlayer),
            requireView().findViewById(R.id.numberOfPlayers),
            requireView().findViewById(R.id.startLife),
            requireView().findViewById(R.id.features)
        ).forEach { item ->
            item.visibility = View.INVISIBLE
            animateCircleMenu(item)
        }

        // toggle the clickable state. View should only be clickable while menu is visible.
        changeMenuVisibilityTo(false)
    }

    // handles the animation of the circle menu
    private fun animateCircleMenu(item: View) {
        val duration = 300L
        val radius = 250

        // from hidden to visible
        var startRadius = 0
        var endRadius = radius
        var startScale = 0f
        var endScale = 1f

        // from visible to hidden
        if (visible) {
            startRadius = radius
            endRadius = 0
            startScale = 1f
            endScale = 0f
        }

        // define scale animation
        val scaleAnimation = ScaleAnimation(
            startScale, endScale,
            startScale, endScale,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        // define radius animation
        val radiusAnim = ValueAnimator.ofInt(startRadius, endRadius)
        radiusAnim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = item.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleRadius = value
            item.layoutParams = layoutParams
        }

        // set duration an interpolation of animation
        scaleAnimation.duration = duration
        scaleAnimation.interpolator = LinearInterpolator()
        radiusAnim.duration = duration
        radiusAnim.interpolator = LinearInterpolator()

        // start animation
        radiusAnim.start()
        item.startAnimation(scaleAnimation)
    }

    private fun changeMenuVisibilityTo(visibility: Boolean) {
        visible = visibility
        requireView().isClickable = visible
        requireView().isFocusable = visible
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