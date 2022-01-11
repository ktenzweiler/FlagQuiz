package com.example.flagquiz.highscores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.flagquiz.R
import com.example.flagquiz.ViewModelFactory
import com.example.flagquiz.data.GameRepo
import com.example.flagquiz.databinding.HighScoresFragmentBinding

class HighScoresFragment : Fragment() {

    companion object {
        fun newInstance() = HighScoresFragment()
    }

    private val viewModel: HighScoresViewModel by viewModels {
        ViewModelFactory(GameRepo())
    }

    private lateinit var binding: HighScoresFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ///////////////////// initialize the binding //////////////////////////
        //////////// uncomment line below ////////////////////////
        // binding = DataBindingUtil.inflate(inflater, R.layout.high_scores_fragment, container, false)


        //////// return the binding root /////////////////
        // return binding.root


        ////////// remove the line below  //////////////////
        return inflater.inflate(R.layout.high_scores_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (score in viewModel.highScores) {
            binding.highScores.text = binding.highScores.text.toString() + ", " + score
        }
    }
}