package com.louischarles.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class SkillLeadersRecyclerAdapter extends RecyclerView.Adapter<SkillLeadersRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<SkillLeadersInfo> mSkillLeaders;
    private LayoutInflater mInflater;

    public SkillLeadersRecyclerAdapter (Context context, List<SkillLeadersInfo> skillLeaders){
        this.mContext = context;
        this.mSkillLeaders = skillLeaders;
        this.mInflater = LayoutInflater.from(this.mContext);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.skill_i_q_leaders_item, parent, false);
//        View view = mInflater.inflate(R.layout.learning_leaders_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String comment = mSkillLeaders.get(position).getScore() + " Skill IQ Score, " + mSkillLeaders.get(position).getCountry();
        holder.mSkillLeaderName.setText(mSkillLeaders.get(position).getName());
        holder.mSkillLeaderComment.setText(comment);
        Picasso.get().load(mSkillLeaders.get(position).getImageUrl()).into(holder.mSkillLeaderImage);
    }

    @Override
    public int getItemCount() {
        return mSkillLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mSkillLeaderName, mSkillLeaderComment;
        private ImageView mSkillLeaderImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSkillLeaderName = itemView.findViewById(R.id.skill_leader_name);
            mSkillLeaderComment = itemView.findViewById(R.id.skill_leader_comment);
            mSkillLeaderImage = itemView.findViewById(R.id.skill_badge_image);
//            mSkillLeaderName = itemView.findViewById(R.id.learner_name);
//            mSkillLeaderComment = itemView.findViewById(R.id.learner_comment);
//            mSkillLeaderImage = itemView.findViewById(R.id.badge_image);
        }
    }
}
