package de.mtgCompanion.android

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import de.mtgCompanion.shared.model.Player

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment : Fragment(R.layout.fragment_player) {

    private lateinit var subbBtn : Button
    private lateinit var addBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_player, container, false)
        val player = Gson().fromJson(requireArguments().getString("player"), Player::class.java)
        view.findViewById<TextView>(R.id.lifeCounter).text = player.lifeCounter.amount.toString()

        subbBtn = view.findViewById(R.id.subbtn)
        addBtn = view.findViewById(R.id.addbtn)

        subbBtn.setOnClickListener{
            view.findViewById<TextView>(R.id.lifeCounter).text = player.alterPlayersLifeTotalBy(-1).toString()
        }

        addBtn.setOnClickListener{
            view.findViewById<TextView>(R.id.lifeCounter).text = player.alterPlayersLifeTotalBy(1).toString()
        }

        view.findViewById<Button>(R.id.subbtn)

        // Inflate the layout for this fragment
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//
//
//        view.findViewById<TextView>(R.id.lifeCounter).text = player.lifeCounter.amount.toString()
//
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Player.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}