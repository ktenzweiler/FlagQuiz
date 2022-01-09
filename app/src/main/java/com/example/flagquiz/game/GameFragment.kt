package com.example.flagquiz.game

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProvider
import com.example.flagquiz.R
import com.example.flagquiz.databinding.GameFragmentBinding
import com.example.flagquiz.score.ScoreFragment
import java.io.InputStream


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(GameViewModel::class.java)
    }
    private val shakeAnimation by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
    }

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // initialize the binding here:
        //binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        viewModel.flagImagePath.observe(viewLifecycleOwner, {
            it?.let {
                binding.flagImage.setImageBitmap(getBitmapFromAssets(it))
            }
        })

        viewModel.selectableAnswers.observe(viewLifecycleOwner, {
            it?.let {
                binding.buttonOne.text = it[0]
                binding.buttonTwo.text = it[1]
                binding.buttonThree.text = it[2]
                binding.buttonFour.text = it[3]
            }
        })

        viewModel.gameOver.observe(viewLifecycleOwner, {

        })

        viewModel.shouldShowWrongAnswerAnimation.observe(viewLifecycleOwner, {

        })

        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    private fun showWrongAnswerAnimation() {
        binding.root.startAnimation(shakeAnimation)
        viewModel.handleAnimationFinished()
    }

    private fun navigateToScoresScreen(wrongAnswers: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ScoreFragment.newInstance(wrongAnswers))
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonClickListener = View.OnClickListener {
            viewModel.handleAnswerSelected((it as Button).text.toString())
        }

        binding.buttonOne.setOnClickListener(buttonClickListener)
        binding.buttonTwo.setOnClickListener(buttonClickListener)
        binding.buttonThree.setOnClickListener(buttonClickListener)
        binding.buttonFour.setOnClickListener(buttonClickListener)

        viewModel.setupGame()
    }

    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        val inputStream: InputStream =
            requireActivity().application.assets.open("countries/$fileName")
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        return bitmap
    }
}