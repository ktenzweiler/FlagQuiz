package com.example.flagquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flagquiz.data.GameRepo
import com.example.flagquiz.highscores.HighScoresFragment
import com.example.flagquiz.highscores.HighScoresViewModel
import com.example.flagquiz.home.HomeViewModel
import com.example.flagquiz.score.ScoreViewModel

class ViewModelFactory(private val gameRepo: GameRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == ScoreViewModel::class.java) {
            ScoreViewModel(gameRepo) as T
        } else if (modelClass == HighScoresViewModel::class.java) {
            HighScoresViewModel(gameRepo) as T
        } else {
            super.create(modelClass)
        }
    }
}