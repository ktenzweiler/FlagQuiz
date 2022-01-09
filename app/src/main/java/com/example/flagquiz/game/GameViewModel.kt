package com.example.flagquiz.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.example.flagquiz.data.Country

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val fileNames = application.assets.list("countries")
    private val countries = ArrayList<Country>()
    private val selectedCountries = ArrayList<Country>()
    private var currentPosition = 0
    private val buttonTextList = ArrayList<String>()
    private var currentCountry : Country? = null

    val flagImagePath = MutableLiveData<String>()
    val selectableAnswers = MutableLiveData<ArrayList<String>>()
    val gameOver = MutableLiveData(false)
    val shouldShowWrongAnswerAnimation = MutableLiveData(false)
    var wrongAnswers = 0

    fun setupGame() {
        fileNames?.forEach { filePath ->
            countries.add(Country(filePath, filePath.replace(".png", "")))
        }

        for (i in 1..15) {
            selectedCountries.add(countries.random())
        }
        nextQuestion()
    }

    fun handleAnswerSelected(answer : String) {
        val formattedAnswer = answer.replace(" ", "_")
        currentCountry?.let {
            if (it.name == formattedAnswer) {
                nextQuestion()
            } else {
                wrongAnswers++  // increment the wrong answers counter
                shouldShowWrongAnswerAnimation.postValue(true) // show the animation
                nextQuestion()
            }
        }
    }

    fun handleAnimationFinished() {
        // reset the animation flag
        shouldShowWrongAnswerAnimation.postValue(false)
    }

    private fun nextQuestion() {
        if (currentPosition == selectedCountries.size) {
            gameOver.postValue(true)
        } else {
            buttonTextList.clear()
            val selectedCountry = selectedCountries[currentPosition]
            currentCountry = selectedCountry
            flagImagePath.postValue(selectedCountry.flagImagePath)
            buttonTextList.add(selectedCountry.name.replace("_", "\n"))
            for (i in 1..3) {
                buttonTextList.add(countries.random().name.replace("_", "\n"))
            }

            buttonTextList.shuffle()
            selectableAnswers.postValue(buttonTextList)
            currentPosition++
        }
    }
}