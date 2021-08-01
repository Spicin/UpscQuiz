package com.example.upscquiz.Controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    private static AppController instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;



    public static synchronized AppController getInstance(){
//        if(instance == null){
//            instance = new AppController(context);
//        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }


    @Override
    public void onCreate(){
        super.onCreate();
        instance=this;
    }


}
