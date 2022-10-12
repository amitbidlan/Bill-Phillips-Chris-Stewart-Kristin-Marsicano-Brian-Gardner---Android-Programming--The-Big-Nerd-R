package com.example.chapter1_geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton:Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton : Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf<Question>(
        Question(R.string.question_americas,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_asia,true)
    )
    private var currentIndex = 0
    private fun updateQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size
        val questionTextresId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextresId)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (correctAnswer == userAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)



        trueButton.setOnClickListener {

            //Toast.makeText(this,R.string.true_button,Toast.LENGTH_SHORT).show()
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            //Toast.makeText(this,R.string.false_button,Toast.LENGTH_SHORT).show()
           checkAnswer(false)
        }
        nextButton.setOnClickListener {
            updateQuestion()
        }
        prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            val questionPrevTextsId = questionBank[currentIndex].textResId

                questionTextView.setText(questionPrevTextsId)


        }

    }
}