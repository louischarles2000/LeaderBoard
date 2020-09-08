package com.louischarles.leaderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumberOfTabs;
    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0:
                frag = new LearningLeaders();

                break;
            case 1:
                frag = new SkillIQLeaders();
                break;
            default:
                frag = null;
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
