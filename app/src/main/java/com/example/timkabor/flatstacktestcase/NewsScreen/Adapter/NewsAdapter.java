package com.example.timkabor.flatstacktestcase.NewsScreen.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.timkabor.flatstacktestcase.App;
import com.example.timkabor.flatstacktestcase.NewsScreen.Model.Post;
import com.example.timkabor.flatstacktestcase.R;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;


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
        @BindView(R.id.likeButton) ImageButton likeButton;
        public boolean like = true;
        public MyViewHolder(CardView view) {
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
            likeButton.setImageResource(R.drawable.like);
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateButton();
                }
            });
        }
        private void updateButton()
        {
            Post t_post = descriptions.get(getAdapterPosition());
            if(like)
            {
                final VKRequest request = new VKRequest("likes.add", VKParameters.from(
                        "type", "post",
                        "owner_id", t_post.getOwner_id() * -1,
                        "item_id", t_post.getPost_id()));

                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        likeButton.setImageResource(R.drawable.dislike);
                        like = false;
                    }
                    @Override
                    public void onError(VKError error) {
                        System.out.println(error.toString());
                    }
                });
            }
            else
                {
                    final VKRequest request = new VKRequest("likes.delete", VKParameters.from(
                            "type", "post",
                            "item_id", t_post.getPost_id(),
                            "owner_id", t_post.getOwner_id() * -1));
                    request.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            likeButton.setImageResource(R.drawable.like);
                            like = true;
                        }
                    });

                }
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
