package com.example.timkabor.flatstacktestcase.NewsScreen.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.example.timkabor.flatstacktestcase.NewsScreen.Presenter.NewsActivityPresenter;
import com.example.timkabor.flatstacktestcase.R;


import butterknife.BindView;


public class NewsActivity extends Activity {

    @BindView(R.id.newsRecyclerView) RecyclerView newsRecyclerView;
    private NewsActivityPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_screen);
        presenter = new NewsActivityPresenter(this);
        presenter.getNewsFeed();
    }

    public RecyclerView getNewsRecyclerView() {
        return newsRecyclerView;
    }


}
