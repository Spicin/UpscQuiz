package com.example.upscquiz.Data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.upscquiz.Controller.AppController;
import com.example.upscquiz.Model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/Spicin/JavaQuestionsJSON/main/javaques.JSON";

    public List<Question> getQuestions(final AnswerListAsyncResponse callBack) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, response -> {

            for (int i = 0; i < response.length(); i++) {
                try {
                    Question question = new Question(response.getJSONArray(i).get(0).toString(), response.getJSONArray(i).get(1).toString(),
                            response.getJSONArray(i).get(2).toString(), response.getJSONArray(i).get(3).toString(), response.getJSONArray(i).get(4).toString(), response.getJSONArray(i).get(5).toString());
                    //add question to arraylist/List

                    questionArrayList.add(question);

                    Log.d("Repo", "On create: " + response.getJSONArray(i).get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (null != callBack) callBack.processFinished(questionArrayList);

        }, error -> {

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;

    }
}
