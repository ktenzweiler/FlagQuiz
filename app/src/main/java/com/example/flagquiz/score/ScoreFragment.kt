package com.example.flagquiz.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.flagquiz.R
import com.example.flagquiz.ViewModelFactory
import com.example.flagquiz.data.GameRepo
import com.example.flagquiz.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {

    companion object {
        fun newInstance(wrongAnswers: Int): ScoreFragment {
            val scoreFragment = ScoreFragment()
            val args = Bundle()
            args.putInt("WrongAnswers", wrongAnswers)
            scoreFragment.arguments = args

            return scoreFragment
        }
    }

    private val viewModel: ScoreViewModel by viewModels {
        ViewModelFactory(GameRepo())
    }

    private lateinit var binding: ScoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //////////////// initialize the binding ///////////////////
        //////////// uncomment line below //////////////////////
        //////binding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)

        //////// return the binding root /////////////////
        // return binding.root

        ////////// remove the line below  //////////////////
        return inflater.inflate(R.layout.score_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wrongAnswers = arguments?.getInt("WrongAnswers")
    }
}