package com.louischarles.leaderboard;

class LearnerInfo {
    private String mName;
    private String mTime;
    private String mCountry;
    private String mImageUrl;

    private String mScore;

    public LearnerInfo (){}
    public LearnerInfo(String name, String time, String country){
        this.mName = name;
        this.mTime = time;
        this.mCountry = country;
    }
    public LearnerInfo(String name, String time, String country, String imageUrl){
        this.mName = name;
        this.mTime = time;
        this.mCountry = country;
        this.mImageUrl = imageUrl;
    }
    public String getScore() {
        return mScore;
    }

    public void setScore(String score) {
        mScore = score;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getName() {
        return mName;
    }

    public String getTime() {
        return mTime;
    }

    public String getCountry() {
        return mCountry;
    }
}
