package com.example.flagquiz.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.flagquiz.R
import com.example.flagquiz.ViewModelFactory
import com.example.flagquiz.data.GameRepo
import com.example.flagquiz.databinding.HomeFragmentBinding
import com.example.flagquiz.game.GameFragment
import com.example.flagquiz.highscores.HighScoresFragment

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels {
        ViewModelProvider.NewInstanceFactory()
    }

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //////////////// initialize the binding ///////////////////
        //////////// uncomment line below //////////////////////
        //binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        //////// return the binding root /////////////////
        // return binding.root

        ////////// remove the line below  //////////////////
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //////// create a click listener for binding.startGame button ////////////////
        ////// uncomment line below //////////////
        //binding.startGameBtn


        /////////// create a click listener for binding.highScores button ///////////////////
        /////// uncomment line below ///////////////////////
        //binding.highScoresBtn
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}