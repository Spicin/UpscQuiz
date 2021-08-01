package com.example.upscquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

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
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        questionList =new Repository().getQuestions(questionArrayList -> {
            binding.questionTextview.setText(questionArrayList.get(currentQuestionIndex)
                    .getAnswer());

        });

        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex+1)% questionList.size();
            updateQuestion();

        });

    }

    private void updateQuestion() {
        String question1 = questionList.get(currentQuestionIndex).getAnswer();
        String optionA= questionList.get(currentQuestionIndex).getOptionA();
        String optionB= questionList.get(currentQuestionIndex).getOptionB();
        String optionC= questionList.get(currentQuestionIndex).getOptionC();
        String optionD= questionList.get(currentQuestionIndex).getOptionD();

        binding.questionTextview.setText(question1);
        binding.optionA.setText(optionA);
        binding.optionB.setText(optionB);
        binding.optionC.setText(optionC);
        binding.optionD.setText(optionD);

    }

}