package com.example.timkabor.flatstacktestcase.NewsScreen.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.timkabor.flatstacktestcase.App;
import com.example.timkabor.flatstacktestcase.NewsScreen.Model.Post;
import com.example.timkabor.flatstacktestcase.R;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {

    private ArrayList<Post> descriptions;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.description) TextView description;
        @BindView(R.id.avatar) ImageView avatar;
        @BindView(R.id.groupName) TextView groupName;
        @BindView(R.id.postDate) TextView postDate;

        public MyViewHolder(CardView view) {
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list, parent, false);
        return new MyViewHolder(itemView);
    }
    public NewsAdapter(ArrayList<Post> descriptions) {
        this.descriptions = descriptions;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)  {
        Post res = descriptions.get(position);
        holder.description.setText(res.getText());
        holder.groupName.setText(res.getGroup_name());
        holder.postDate.setText(App.parseUnixTime(res.getDate()));
        App.loadImageFromURL(res.getAvatar(),holder.avatar);
    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }


}
