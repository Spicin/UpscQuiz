package com.example.upscquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.upscquiz.Data.AnswerListAsyncResponse;
import com.example.upscquiz.Data.Repository;
import com.example.upscquiz.Model.Question;
import com.example.upscquiz.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex=0;
    List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        questionList = new Repository().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                updateQuestion();
            }
        });

        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex+1)% questionList.size();
            updateQuestion();
            binding.answerTextView.setVisibility(View.INVISIBLE);


        });

        binding.showAnswerButton.setOnClickListener(view -> {
            String whatsTheAns = questionList.get(currentQuestionIndex).getMyAnswer();
            binding.answerTextView.setText(whatsTheAns);
            binding.answerTextView.setVisibility(View.VISIBLE);
        });

       binding.button.setOnClickListener(view -> {
           if(currentQuestionIndex>0) {
               currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
           }
           updateQuestion();
           binding.answerTextView.setVisibility(View.INVISIBLE);
       });

    }

    private void updateQuestion() {
        String question1 = questionList.get(currentQuestionIndex).getAnswer();
        String optionA= questionList.get(currentQuestionIndex).getOptionA();
        String optionB= questionList.get(currentQuestionIndex).getOptionB();
        String optionC= questionList.get(currentQuestionIndex).getOptionC();
        String optionD= questionList.get(currentQuestionIndex).getOptionD();

        binding.questionTextview.setText(question1);
        binding.optionA.setText("A: "+ optionA);
        binding.optionB.setText("B: " + optionB);
        binding.optionC.setText("C: "+optionC);
        binding.optionD.setText("D: "+optionD);

    }

}