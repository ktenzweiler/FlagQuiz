package com.example.flagquiz.highscores

import androidx.lifecycle.ViewModel
import com.example.flagquiz.data.GameRepo

class HighScoresViewModel internal constructor(gameRepo: GameRepo): ViewModel() {

    val highScores :ArrayList<String> = gameRepo.fetchHighScores()
}