package com.example.upscquiz.Data;

import com.example.upscquiz.Model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
