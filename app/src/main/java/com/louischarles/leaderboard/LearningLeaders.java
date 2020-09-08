package com.louischarles.leaderboard;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LearningLeaders extends Fragment {

    private RecyclerView mLearnersRecyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View mView;
//    private LeaderBoardRequestQueue mQueueInstance;
    private List<LearnerInfo> mLearnersList;
    private static final String HOURS_URL = "https://gadsapi.herokuapp.com/api/hours";

    public LearningLeaders() {
        // Required empty public constructor
    }

    public static LearningLeaders newInstance(String param1, String param2) {
        LearningLeaders fragment = new LearningLeaders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
//        mQueueInstance.getRequestQueue().stop();
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchLearningLeaders();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
//        mQueueInstance = LeaderBoardRequestQueue.getInstance(mView.getContext().getApplicationContext());
        mLearnersRecyclerView = (RecyclerView) mView.findViewById(R.id.learners_list);
        mLearnersList = new ArrayList<>();
        return mView;
    }


    private void fetchLearningLeaders() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, HOURS_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Do something with the response
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        LearnerInfo learner = new LearnerInfo();

                        //Set the String values to LearnerInfo from the JSON Object
                        learner.setName(jsonObject.getString("name"));
                        learner.setTime(jsonObject.getString("hours"));
                        learner.setImageUrl(jsonObject.getString("badgeUrl"));
                        learner.setCountry(jsonObject.getString("country"));

                        //Add learner to the list field
                        mLearnersList.add(learner);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                displayLearners();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("tag", "There's something wrong =>  " + error.getMessage());
            }
        });
        LeaderBoardRequestQueue.getInstance(mView.getContext()).addRequestToQueue(jsonArrayRequest);
//        mQueueInstance.addRequestToQueue(jsonArrayRequest);
    }


    private void displayLearners() {
        //Set adapter to the recycler view
        mLearnersRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext().getApplicationContext()));
//        Collections.sort(mLearnersList, new Comparator<LearnerInfo>() {
//            @Override
//            public int compare(LearnerInfo lhs, LearnerInfo rhs) {
//                return lhs.getTime().compareTo(rhs.getTime());
//            }
//        });
        mLearnersList.sort(new Comparator<LearnerInfo>() {
            @Override
            public int compare(LearnerInfo o1, LearnerInfo o2) {
                return Integer.valueOf(o2.getTime()).compareTo(Integer.valueOf(o1.getTime()));
            }
        });
        LearnersRecyclerAdapter adapter = new LearnersRecyclerAdapter(mView.getContext().getApplicationContext(), mLearnersList);
        mLearnersRecyclerView.setAdapter(adapter);
    }

}


