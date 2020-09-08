package com.louischarles.leaderboard;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class LeaderBoardRequestQueue {
    private static LeaderBoardRequestQueue mInstance;
    private final Context mContext;
    private  RequestQueue mRequestQueue;

    private LeaderBoardRequestQueue (Context context){
        this.mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static LeaderBoardRequestQueue getInstance(Context context){
        if(mInstance == null){
            mInstance = new LeaderBoardRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return mRequestQueue;
    }

    public <T> void addRequestToQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
