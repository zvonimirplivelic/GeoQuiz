package com.zvonimirplivelic.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var score = 0
    private var questionsAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle?) called")
        Log.d(TAG, "questbank ${questionBank.size}")
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
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        previousButton.setOnClickListener {
            currentIndex = if (questionBank.size >= currentIndex) {
                questionBank.size - 1
            } else {
                (currentIndex - 1) % questionBank.size
            }
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        displayAnswerButtons()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val messageResId: Int
        questionsAnswered++

        Log.d(TAG, "questAnswered: $questionsAnswered")

        val correctAnswer = questionBank[currentIndex].answer

        messageResId = if (userAnswer == correctAnswer) {
            score++
            Log.d(TAG, "score: $score")
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        if (questionsAnswered == questionBank.size) {
            Toast.makeText(
                this,
                "Your score is $score / ${questionBank.size}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        }
        isCurrentQuestionAnswered()
        displayAnswerButtons()
    }

    private fun isCurrentQuestionAnswered() {
        questionBank[currentIndex].isQuestionAnswered = true
    }

    private fun displayAnswerButtons() {
        if (questionBank[currentIndex].isQuestionAnswered) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
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