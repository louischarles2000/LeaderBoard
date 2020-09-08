package com.louischarles.leaderboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import java.util.Comparator;
import java.util.List;


public class SkillIQLeaders extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View mView;
//    private LeaderBoardRequestQueue mQueueInstance;
    private RecyclerView mSkillLeadersRecyclerView;
    private List<SkillLeadersInfo> mSkillLeadersList;
    private static final String SKILL_URL = "https://gadsapi.herokuapp.com/api/skilliq";

    public SkillIQLeaders() {
        // Required empty public constructor
    }

    public static SkillIQLeaders newInstance(String param1, String param2) {
        SkillIQLeaders fragment = new SkillIQLeaders();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        fetchSkillLeadersData();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
//        mQueueInstance = LeaderBoardRequestQueue.getInstance(mView.getContext().getApplicationContext());
        mSkillLeadersRecyclerView = (RecyclerView) mView.findViewById(R.id.skill_leaders_list);
        mSkillLeadersList = new ArrayList();
        return mView;
    }

    @Override
    public void onDestroy() {
//        mQueueInstance.getRequestQueue().stop();
        super.onDestroy();
    }


    private void fetchSkillLeadersData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, SKILL_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Do something with the response
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObject = response.getJSONObject(i);
                        SkillLeadersInfo learner = new SkillLeadersInfo();

                        //Set the String values to LearnerInfo from the JSON Object
                        learner.setName(responseObject.getString("name"));
                        learner.setScore(responseObject.getString("score"));
                        learner.setCountry(responseObject.getString("country"));
                        learner.setImageUrl(responseObject.getString("badgeUrl"));

                        mSkillLeadersList.add(learner);
                    } catch (JSONException e) {
                        Log.d("tag", "onErrorException" + e.getMessage());
                    }
                }
                displaySkillLeaders();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorException" + error.getMessage());
            }
        });
//        mQueueInstance.addRequestToQueue(jsonArrayRequest);
        LeaderBoardRequestQueue.getInstance(mView.getContext()).addRequestToQueue(jsonArrayRequest);
    }

    private void displaySkillLeaders() {
        //Set adapter to the recycler view
        mSkillLeadersRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext().getApplicationContext()));
        mSkillLeadersList.sort(new Comparator<SkillLeadersInfo>() {
            @Override
            public int compare(SkillLeadersInfo o1, SkillLeadersInfo o2) {
                return Integer.valueOf(o2.getScore()).compareTo(Integer.valueOf(o1.getScore()));
            }
        });
        SkillLeadersRecyclerAdapter adapter = new SkillLeadersRecyclerAdapter(mView.getContext().getApplicationContext(), mSkillLeadersList);
        mSkillLeadersRecyclerView.setAdapter(adapter);
    }
}
