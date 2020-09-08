package com.louischarles.leaderboard;

import java.util.ArrayList;

public final class DataManager {
    public static ArrayList<LearnerInfo> mLearners;

    public static DataManager getInstance(){
        initializeList();
        return new DataManager();
    }

    private static void initializeList() {
        addLeaner("Louis Charles", "200", "Uganda");
        addLeaner("John Charles", "100", "Kenya");
        addLeaner("Davido Charles", "300", "Nigeria");
        addLeaner("Kiddo Something", "250", "Uganda");
    }

    private static void addLeaner(String name, String time, String country) {
        LearnerInfo learner = new LearnerInfo(name, time, country);
        mLearners.add(learner);
    }

    public static ArrayList<LearnerInfo> getLearners(){
        return mLearners;
    }

}
