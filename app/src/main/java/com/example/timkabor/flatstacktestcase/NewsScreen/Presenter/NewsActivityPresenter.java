package com.example.timkabor.flatstacktestcase.NewsScreen.Presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.timkabor.flatstacktestcase.NewsScreen.Adapter.NewsAdapter;
import com.example.timkabor.flatstacktestcase.NewsScreen.Model.Post;
import com.example.timkabor.flatstacktestcase.NewsScreen.View.NewsActivity;
import com.example.timkabor.flatstacktestcase.R;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class NewsActivityPresenter implements INewsActivityPresenter {
    private NewsActivity activity;
    private ArrayList<Post> descriptions = new ArrayList<>();

    public NewsActivityPresenter(NewsActivity activity){
        this.activity = activity;
        ButterKnife.bind(activity);

    }
    public void getNewsFeed(){
        final VKRequest request = new VKRequest("newsfeed.get");
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                JSONObject jObject = response.json;
                try {
                    parseJson(jObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                createRecycler();
            }
        });
    }
    private void createRecycler(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        activity.getNewsRecyclerView().setLayoutManager(mLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(descriptions);
        activity.getNewsRecyclerView().setAdapter(newsAdapter);
    }
    private void parseJson(JSONObject result) throws JSONException {
        JSONArray posts  = result.getJSONObject("response").getJSONArray("items"),
                  groups = result.getJSONObject("response").getJSONArray("groups");
        Post new_post;
        for(int i=0;i<posts.length();i++)
        {
            JSONObject post = posts.getJSONObject(i);
            String type = post.getString("type");
            int source_id = post.getInt("source_id");

            //parse posts to get description, owner id and date
            if(type.equals("post") && source_id < 0) {
                new_post = new Post();
                new_post.setText(post.getString("text"));
                new_post.setOwner_id(Math.abs(post.getInt("source_id")));
                new_post.setDate(post.getInt("date"));
                new_post.setPost_id(post.getInt("post_id"));
                //parse groups to find owner avatar and name
                for (int j = 0; j < groups.length(); j++) {
                    JSONObject group = groups.getJSONObject(j);
                    if (group.getInt("id") == new_post.getOwner_id()) {
                        new_post.setAvatar(group.getString("photo_50"));
                        new_post.setGroup_name(group.getString("name"));
                    }
                }
                if (!new_post.getText().equals("")) descriptions.add(new_post);
            }
        }
    }
}
