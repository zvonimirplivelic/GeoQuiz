package com.zvonimirplivelic.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        val quizViewModel: QuizViewModel by lazy {
            ViewModelProvider(this)[QuizViewModel::class.java]
        }

        Log.d(TAG, "onCreate(Bundle?) called")
        Log.d(TAG, "QVM: $quizViewModel")
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
//        displayAnswerButtons()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val messageResId: Int
//        quizViewModel.questionsAnswered++

//        Log.d(TAG, "questAnswered: ${quizViewModel.questionsAnswered}")

        val correctAnswer = quizViewModel.currentQuestionAnswer

        messageResId = if (userAnswer == correctAnswer) {
//            quizViewModel.score++
//            Log.d(TAG, "score: ${quizViewModel.score}")
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

//        if (quizViewModel.questionsAnswered == quizViewModel.questionBank.size) {
//            Toast.makeText(
//                this,
//                "Your score is ${quizViewModel.score} / ${quizViewModel.questionBank.size}",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
//        }
//        quizViewModel.questionAnswered()
//        displayAnswerButtons()
    }



//    private fun displayAnswerButtons() {
//        Log.d(TAG, "displayAnswerButtons: ${quizViewModel.isCurrentQuestionAnswered}")
//        if (quizViewModel.isCurrentQuestionAnswered) {
//            trueButton.isEnabled = false
//            falseButton.isEnabled = false
//        } else {
//            trueButton.isEnabled = true
//            falseButton.isEnabled = true
//        }
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}