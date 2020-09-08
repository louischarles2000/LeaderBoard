package com.louischarles.leaderboard;

public class SkillLeadersInfo {
    private String mName;
    private String mCountry;
    private String mImageUrl;
    private String mScore;

    public SkillLeadersInfo (){}

    public SkillLeadersInfo(String name, String country, String imageUrl, String score){
        this.mName = name;
        this.mScore = score;
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
    public void setCountry(String country) {
        mCountry = country;
    }

    public String getName() {
        return mName;
    }

    public String getCountry() {
        return mCountry;
    }
}
