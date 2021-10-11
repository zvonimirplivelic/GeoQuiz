package com.zvonimirplivelic.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex = 0
//    var score = 0
//    var questionsAnswered = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = if (questionBank.size >= currentIndex) {
            questionBank.size - 1
        } else {
            (currentIndex - 1) % questionBank.size
        }
    }

//    fun questionAnswered() {
//        var isCurrentQuestionAnswered: Boolean = questionBank[currentIndex].isQuestionAnswered
//
//        if (!isCurrentQuestionAnswered) {
//        }
//
//        Log.d(TAG, "QB: $questionBank")
//    }

}