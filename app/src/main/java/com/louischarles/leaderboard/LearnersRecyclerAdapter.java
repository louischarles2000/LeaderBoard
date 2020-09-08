package com.louischarles.leaderboard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.ViewHolder> {
    private  Context mContext;
    private final LayoutInflater mInflater;
    private List<LearnerInfo> mLearners;

    public LearnersRecyclerAdapter(Context context, List<LearnerInfo> learners){
        this.mContext = context;
        this.mLearners = learners;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = mInflater.inflate(R.layout.learning_leaders_item, parent, false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String comment =  mLearners.get(position).getTime() +" learning hours, " + mLearners.get(position).getCountry();
        holder.mTextName.setText(String.valueOf(mLearners.get(position).getName()));
        holder.mTextComment.setText(comment);
        Picasso.get().load(mLearners.get(position).getImageUrl()).into(holder.mBadgeImage);
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextName, mTextComment;
        public final ImageView mBadgeImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.learner_name);
            mTextComment = (TextView) itemView.findViewById(R.id.learner_comment);
            mBadgeImage = (ImageView) itemView.findViewById(R.id.badge_image);
        }
    }
}
